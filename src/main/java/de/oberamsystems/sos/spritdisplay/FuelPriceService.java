package de.oberamsystems.sos.spritdisplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelPriceService {
	
	@Autowired
	private FuelPriceRepository repository;
	
	public FuelPrice saveFuelPrice(FuelPrice service) {
        return repository.save(service);
    }

    public List<FuelPrice> getAllFuelPrices() {
        return (List<FuelPrice>) repository.findAll();
    }

    public FuelPrice getFuelPriceById(int id) {
        return repository.findById(id);
    }

    public void deleteFuelPriceById(int id) {
    	repository.deleteById(id);
    }
}