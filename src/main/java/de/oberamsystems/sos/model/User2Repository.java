package de.oberamsystems.sos.model;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface User2Repository extends CrudRepository<User2, Long> {
}