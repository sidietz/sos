package de.oberamsystems.sos.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyProcessRepository extends JpaRepository<MyProcess, Long> {
	List<MyProcess> findByName(String Name);
	MyProcess findById(long id);
}
