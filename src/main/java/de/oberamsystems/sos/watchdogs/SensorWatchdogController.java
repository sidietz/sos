package de.oberamsystems.sos.watchdogs;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.oberamsystems.sos.db.DbReader;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectService;
import de.oberamsystems.sos.model.NotRunner;
import de.oberamsystems.sos.model2.Sensor2;
import de.oberamsystems.sos.model2.Sensor2Builder;

@Component
public class SensorWatchdogController implements IWatchdogController {

	private static final Logger log = LoggerFactory.getLogger(SensorWatchdogController.class);

	protected DbObjectService dbObjectService;

	public SensorWatchdogController() {
	}

	public SensorWatchdogController(DbObjectService dbObjectService) {
		this.dbObjectService = dbObjectService;
	}

	public void check() {
		
		DbObject dbobj = dbObjectService.getDbObjectsByName("sensors2").get(0);
	
		if (checkDb()) {
			dbobj.setRunning(true);
		} else {
			dbobj.setRunning(false);
			log.info(String.format("DB-Object '%s' not running!", "sensor2"));
		}
		
		dbObjectService.saveDbObject(dbobj);
	}

	private boolean checkDb() {
		LocalDateTime date2 = LocalDateTime.now();
		LocalDateTime date1 = date2.minusMinutes(15);

		String query = "SELECT * FROM sensors2";
		query = "SELECT * FROM sensors2  WHERE date > ?::timestamp AND date < ?::timestamp AND sensor='s8'";
		List<String> params = new ArrayList<String>();
		params.add(date1.toString());
		params.add(date2.toString());

		DbReader dbR = new DbReader("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/mqtt", "simon",
				"N0m1596.");
		ResultSet rs = dbR.execute(query, params);

		if (rs == null) {
			return false;
		}

		List<Sensor2> sensor2s = Sensor2Builder.build(rs);
		if (sensor2s.size() <= 2) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<NotRunner> getNotRunners() {
		List<NotRunner> notRunners = new ArrayList<NotRunner>();
		if (checkDb()) {
			;
		} else {
			notRunners.add(new NotRunner(new DbObject("sensor2"), null, null));
		}
		return notRunners;
	}

}
