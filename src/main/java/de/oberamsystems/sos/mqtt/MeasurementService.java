package de.oberamsystems.sos.mqtt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {
	
	@Autowired
	private MeasurementRepository repository;
	
	public Measurement saveMeasurement(Measurement service) {
        return repository.save(service);
    }

    public List<Measurement> getAllMeasurements() {
        return (List<Measurement>) repository.findAll();
    }

    public Measurement getMeasurementById(int id) {
        return repository.findById(id);
    }

    public void deleteMeasurementById(int id) {
    	repository.deleteById(id);
    }
}
