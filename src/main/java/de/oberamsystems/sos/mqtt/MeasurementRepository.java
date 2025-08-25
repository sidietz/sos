package de.oberamsystems.sos.mqtt;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {
	List<Measurement> findByDateTime(LocalDateTime date);
	Measurement findById(int id);
}
