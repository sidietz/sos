package de.oberamsystems.sos.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyHttpServiceRepository extends JpaRepository<MyHttpService, Long> {
	List<MyHttpService> findByName(String Name);
	MyHttpService findById(long id);
}
