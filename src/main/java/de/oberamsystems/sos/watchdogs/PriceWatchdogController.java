package de.oberamsystems.sos.watchdogs;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.oberamsystems.sos.db.DbReader;
import de.oberamsystems.sos.mail.EmailService;
import de.oberamsystems.sos.mail.Mailer;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectService;
import de.oberamsystems.sos.model2.Price;
import de.oberamsystems.sos.model2.PriceBuilder;

@Component
public class PriceWatchdogController implements IWatchdogController {

	private static final Logger log = LoggerFactory.getLogger(PriceWatchdogController.class);

	private DbObjectService dbObjectService;
	private EmailService mailer;
	
	//private EmailService emailService;
	

	public PriceWatchdogController() {
	}

	public PriceWatchdogController(DbObjectService dbObjectService, EmailService mailer) {
		this.dbObjectService = dbObjectService;
		this.mailer = mailer;
	}

	@Override
	public void check() {
		LocalDateTime date2 = LocalDateTime.now();
		LocalDateTime date1 = date2.minusMinutes(15);

		String query = "SELECT * FROM prices";
		query = "SELECT * FROM prices  WHERE changed_at > ?::timestamp AND changed_at < ?::timestamp AND station_id=0";
		List<String> params = new ArrayList<String>();
		params.add(date1.toString());
		params.add(date2.toString());

		DbReader dbR = new DbReader("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/spritdisplay", "simon",
				"N0m1596.");
		ResultSet rs = dbR.execute(query, params);

		DbObject dbobj = dbObjectService.getDbObjectsByName("price").get(0);

		if (rs == null) {
			dbobj.setRunning(false);
			return;
		}
		List<Price> sensor2s = PriceBuilder.build(rs);
		log.debug(String.format("found price entries: '%d'", sensor2s.size()));

		if (sensor2s.size() <= 2) {
			dbobj.setRunning(false);
			dbObjectService.saveDbObject(dbobj);
			//System.out.println(dbobj);

			log.warn("price not running");
			String msg =  String.format("'%s' '%s' läuft nicht", "price", dbobj.getDbName());
			mailer.sendEmail("simon.dietz@vodafone.de", "Test", msg);
		} else {
			dbobj.setRunning(true);
			dbObjectService.saveDbObject(dbobj);
		}
	}

}
