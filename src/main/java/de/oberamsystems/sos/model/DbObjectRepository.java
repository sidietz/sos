package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DbObjectRepository extends CrudRepository<DbObject, Long> {
	List<DbObject> findByDbName(String dbName);
	DbObject findById(long id);
}
