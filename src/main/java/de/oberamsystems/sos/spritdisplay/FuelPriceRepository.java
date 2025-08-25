package de.oberamsystems.sos.spritdisplay;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelPriceRepository extends JpaRepository<FuelPrice, Integer> {
	List<FuelPrice> findByDateTime(LocalDateTime date);
	FuelPrice findById(int id);
}
