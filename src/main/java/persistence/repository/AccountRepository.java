package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.model.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByCustomerId(Integer customerId);
}
