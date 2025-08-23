package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServiceService {
	
	@Autowired
	private MyServiceRepository repository;
	
    public MyService saveService(MyService service) {
        return repository.save(service);
    }

    public List<MyService> getAllServices() {
        return (List<MyService>) repository.findAll();
    }

    public MyService getServiceById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteServiceById(Long id) {
    	repository.deleteById(id);
    }

}
