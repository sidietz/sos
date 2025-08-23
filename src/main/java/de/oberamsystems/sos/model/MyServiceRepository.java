package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MyServiceRepository extends CrudRepository<MyService, Long> {
	List<MyService> findByName(String Name);
	MyService findById(long id);
}
