package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ProductDao;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, UUID> {

}
