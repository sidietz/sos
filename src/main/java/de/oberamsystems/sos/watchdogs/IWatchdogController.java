package de.oberamsystems.sos.watchdogs;

import java.util.List;

import de.oberamsystems.sos.model.NotRunner;

public interface IWatchdogController {

	void check();
	List<NotRunner> getNotRunners();

}