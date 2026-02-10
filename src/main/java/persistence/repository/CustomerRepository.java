package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import persistence.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
