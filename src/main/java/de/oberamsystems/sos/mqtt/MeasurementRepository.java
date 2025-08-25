package de.oberamsystems.sos.mqtt;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
	List<Measurement> findByDateTime(LocalDateTime date);
	Measurement findById(int id);
}
