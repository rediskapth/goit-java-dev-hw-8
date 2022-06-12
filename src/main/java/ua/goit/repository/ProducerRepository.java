package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ProducerDao;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProducerRepository extends CrudRepository<ProducerDao, UUID> {

    Optional<ProducerDao> findProducerByName(String name);
}
