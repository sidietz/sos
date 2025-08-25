package de.oberamsystems.sos.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyServiceRepository extends JpaRepository<MyService, Long> {
	List<MyService> findByName(String Name);
	MyService findById(long id);
}
