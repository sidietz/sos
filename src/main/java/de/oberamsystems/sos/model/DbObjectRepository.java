package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbObjectRepository extends JpaRepository<DbObject, Long> {
	List<DbObject> findByDbName(String dbName);
	Watchable findById(long id);
	List<DbObject> getByName(String name);
}
