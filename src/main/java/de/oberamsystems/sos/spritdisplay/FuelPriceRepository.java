package de.oberamsystems.sos.spritdisplay;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FuelPriceRepository extends CrudRepository<FuelPrice, Integer> {
	List<FuelPrice> findByDateTime(LocalDateTime date);
	FuelPrice findById(int id);
}
