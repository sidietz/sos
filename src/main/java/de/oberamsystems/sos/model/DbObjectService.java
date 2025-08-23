package de.oberamsystems.sos.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbObjectService {
	
	@Autowired
	private DbObjectRepository repository;
	
    public DbObject saveDbObject(DbObject service) {
        return repository.save(service);
    }

    public List<DbObject> getAllDbObjects() {
        return (List<DbObject>) repository.findAll();
    }
    
    public List<DbObject> getDbObjectsByName(String name) {
        return (List<DbObject>) repository.findByDbName(name);
    }

    public DbObject getDbObjectById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteDbObjectById(Long id) {
    	repository.deleteById(id);
    }

}
