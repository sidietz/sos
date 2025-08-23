package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MyProcessRepository extends CrudRepository<MyProcess, Long> {
	List<MyProcess> findByName(String Name);
	MyProcess findById(long id);
}
