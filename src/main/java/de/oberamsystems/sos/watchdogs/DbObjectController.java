package de.oberamsystems.sos.watchdogs;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.db.DbReader;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectService;
import de.oberamsystems.sos.model.NotRunner;
import de.oberamsystems.sos.model2.DbEntry;
import de.oberamsystems.sos.model2.Price;
import de.oberamsystems.sos.model2.Sensor2;

public class DbObjectController implements IWatchdogController {

	private static final Logger log = LoggerFactory.getLogger(DbObjectController.class);

	private DbObjectService dbObjectService;
	
	private Price price;// = new Price();
	private Sensor2 sensor2;// = new Sensor2();

	private String type;

	public DbObjectController() {
	}

	public DbObjectController(DbObjectService dbObjectService, String type) {
		this.dbObjectService = dbObjectService;
		this.type = type;
		
		if (type.equals("price") ) {
			price = new Price();
		} else if (type.equals("sensor2")) {
			sensor2 = new Sensor2();
		} else {
			new IllegalArgumentException(String.format("'s' is not a valid DbObject type!", type));
		}
	}
	
	private String getTableNameFromType() {
		return type.equals("price") ? "price" : "sensors2";
	}
	
	private String getDbNameFromType() {
		return type.equals("price") ? "spritdisplay" : "mqtt";
	}
	
	private String getDbQueryFromType() {
		return type.equals("price") ? "SELECT * FROM prices WHERE changed_at > ?::timestamp AND changed_at < ?::timestamp AND station_id=0" : "SELECT * FROM sensors2  WHERE date > ?::timestamp AND date < ?::timestamp AND sensor='s8'";
	}

	@Override
	public void check() {
		DbObject dbobj = dbObjectService.getDbObjectsByName(getTableNameFromType()).get(0);
		boolean checkedDb = false;
		
		if (type.equals("price") ) {
			price = new Price();
			checkedDb = checkDb(price);
		} else if (type.equals("sensor2")) {
			sensor2 = new Sensor2();
			checkedDb = checkDb(sensor2);
		}

		if (checkedDb) {
			dbobj.setRunning(true);
		} else {
			dbobj.setRunning(false);
			log.info(String.format("DB-Object '%s' not running!", "price"));
		}

		dbObjectService.saveDbObject(dbobj);
	}

	public boolean checkDb(DbEntry c) {
		LocalDateTime date2 = LocalDateTime.now();
		LocalDateTime date1 = date2.minusMinutes(15);

		String query = getDbQueryFromType();
		List<String> params = new ArrayList<String>();
		log.debug(String.format("Type is: '%s'", type));
		String dbName = getDbNameFromType();
		log.debug(String.format("DB-Name is: '%s'", dbName));
		params.add(date1.toString());
		params.add(date2.toString());

		try {
			DbReader dbR = new DbReader("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/" + getDbNameFromType(),
					"simon", "N0m1596.");
			ResultSet rs = dbR.execute(query, params);

			List<DbEntry> dbEntries = c.build(rs);
			if (rs == null) {
				return false;
			}

			List<DbEntry> sensor2s = dbEntries;
			if (sensor2s.size() <= 2) {
				return false;
			} else {
				return true;
			}
		} catch (IllegalArgumentException e) {
			log.warn(String.format("DbObjectService error message: ", e.getMessage()));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<NotRunner> getNotRunners() {
		List<NotRunner> notRunners = new ArrayList<NotRunner>();
		
		boolean checkedDb = false;
		
		if (type.equals("price") ) {
			checkedDb = checkDb(price);
		} else if (type.equals("sensor2")) {
			checkedDb = checkDb(sensor2);
		}
		
		if (checkedDb) {
			;
		} else {
			notRunners.add(new NotRunner(new DbObject(type), null, null));
		}

		return notRunners;
	}

}
