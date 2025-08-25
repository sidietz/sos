package de.oberamsystems.sos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.oberamsystems.sos.mail.EmailService;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyServiceRepository;
import de.oberamsystems.sos.model.NotRunner;
import de.oberamsystems.sos.model.NotRunnerManager;
import de.oberamsystems.sos.watchdogs.DbObjectController;
import de.oberamsystems.sos.watchdogs.IWatchdogController;
import de.oberamsystems.sos.watchdogs.PsWatchdogController;
import de.oberamsystems.sos.watchdogs.SystemdWatchdogController;

@Component
@EnableScheduling
public class MasterScheduler {

	private static final Logger log = LoggerFactory.getLogger(MasterScheduler.class);

	@Autowired
	private MyProcessRepository procRepo;

	@Autowired
	private MyServiceRepository serviceRepo;

	@Autowired
	private DbObjectRepository dbObjectRepo;

	@Autowired
	private NotRunnerManager notRunnerService;

	@Autowired
	private EmailService mailer;

	@Scheduled(fixedRate = 15 * 1000)
	public int scheduleTest() {

		List<NotRunner> oldNotRunners = notRunnerService.getNotRunners();
		List<NotRunner> newNotRunners = Collections.synchronizedList(new ArrayList<NotRunner>());

		log.debug("run scheduled tasks");
		IWatchdogController pwc = new PsWatchdogController(procRepo);
		pwc.check();
		IWatchdogController swc = new SystemdWatchdogController(serviceRepo);
		swc.check();
		IWatchdogController dbc2 = new DbObjectController(dbObjectRepo, "sensor2");
		dbc2.check();
		IWatchdogController pc = new DbObjectController(dbObjectRepo, "price");
		pc.check();

		List<NotRunner> tmp = visitController(pwc);
		newNotRunners.addAll(tmp);
		tmp = visitController(swc);
		newNotRunners.addAll(tmp);
		tmp = visitController(dbc2);
		newNotRunners.addAll(tmp);
		tmp = visitController(pc);
		newNotRunners.addAll(tmp);

		List<NotRunner> wentRunning = wentRunning(oldNotRunners, newNotRunners);
		List<NotRunner> wentNotRunning = wentNotRunning(oldNotRunners, newNotRunners);

		for (NotRunner nr : wentNotRunning) {
			nr.setNotRunningSince(LocalDateTime.now());
			log.warn(String.format("'%s' '%s' went not running", nr.getWatchable().getKind(),
					nr.getWatchable().getName()));
		}

		for (NotRunner nr : wentRunning) {
			log.warn(String.format("'%s' '%s' went running, again", nr.getWatchable().getKind(),
					nr.getWatchable().getName()));
			notRunnerService.deleteNotRunnerById(nr);
		}

		List<NotRunner> stillNotRunning = new ArrayList<NotRunner>(notRunnerService.getNotRunners());

		
		List<NotRunner> toDelete = new ArrayList<NotRunner>();

		for (NotRunner nr : wentNotRunning) {
			if (stillNotRunning.contains(nr)) {
				toDelete.add(nr);
			}
		}
		
		for (NotRunner nr : toDelete) {
			stillNotRunning.remove(nr);
			stillNotRunning.remove(nr);
		}

		for (NotRunner nr : wentNotRunning) {
			nr.setDuration(Duration.between(nr.getNotRunningSince(), LocalDateTime.now()));
			notRunnerService.addNotRunner(nr);
		}

		sendNrMail(wentNotRunning, wentRunning, stillNotRunning);

		return 0;
	}

	private static void printNotRunners(List<NotRunner> oldNr) {
		for (NotRunner nr : oldNr) {
			System.out.println(nr);
		}
	}

	public static List<NotRunner> wentNotRunning(List<NotRunner> oldNr, List<NotRunner> newNr) {
		List<NotRunner> wentNotRunning = Collections.synchronizedList(new ArrayList<NotRunner>());
		for (NotRunner nr : newNr) {
			if (!oldNr.contains(nr)) {
				wentNotRunning.add(nr);
				nr.setNotRunningSince(LocalDateTime.now());
				nr.setDuration(Duration.between(nr.getNotRunningSince(), LocalDateTime.now()));
			}
		}
		return wentNotRunning;
	}

	public static List<NotRunner> wentRunning(List<NotRunner> oldNr, List<NotRunner> newNr) {
		List<NotRunner> wentRunning = Collections.synchronizedList(new ArrayList<NotRunner>());
		for (NotRunner nr : oldNr) {
			if (!newNr.contains(nr)) {
				wentRunning.add(nr);
			}
		}
		return wentRunning;
	}

	private static List<NotRunner> visitController(IWatchdogController controller) {
		return controller.getNotRunners();
	}

	private void sendNrMail(NotRunner nr) {

		String subject = getSubject(nr);
		String body = getBody(nr);

		mailer.sendEmail(subject, body);

	}

	private void sendNrMail(List<NotRunner> nrs) {

		if (nrs.isEmpty()) {
			return;
		}

		String subject = "[SOS] not running stuff";

		mailer.sendHtmlEmail(subject, nrs);
	}

	private void sendNrMail(List<NotRunner> nrs, List<NotRunner> nrs2) {

		if (nrs.isEmpty() && nrs2.isEmpty()) {
			return;
		}

		String subject = "[SOS] not running stuff";

		mailer.sendHtmlEmail(subject, nrs, nrs2);
	}

	private void sendNrMail(List<NotRunner> nrs, List<NotRunner> nrs2, List<NotRunner> nrs3) {

		if (nrs.isEmpty() && nrs2.isEmpty()) {
			return;
		}

		String subject = "[SOS] not running stuff";

		mailer.sendHtmlEmail(subject, nrs, nrs2, nrs3);
	}

	private String getSubject(NotRunner nr) {
		return String.format("[SOS] '%s' '%s' died", nr.getWatchable().getKind(), nr.getWatchable().getName());
	}

	private String getBody(NotRunner nr) {
		return String.format("The '%s' '%s' died\nand is not running since '%s'", nr.getWatchable().getKind(),
				nr.getWatchable().getName(), humanReadableFormat(nr.getDuration()));
	}

	private static String humanReadableFormat(Duration duration) {
		return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
	}

}
