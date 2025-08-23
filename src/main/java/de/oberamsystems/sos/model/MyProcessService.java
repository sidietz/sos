package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyProcessService {
	
	@Autowired
	private MyProcessRepository repository;
	
    public MyProcess saveProcess(MyProcess myProcess) {
        return repository.save(myProcess);
    }

    public List<MyProcess> getAllProcesses() {
        return (List<MyProcess>) repository.findAll();
    }

    public MyProcess getProcessById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteProcessById(Long id) {
    	repository.deleteById(id);
    }

}
