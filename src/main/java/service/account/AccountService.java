package service.account;

import common.DTO.AccountDTO;
import common.error.BusinessException;
import common.error.ElementNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.model.Account;
import persistence.model.Customer;
import persistence.repository.AccountRepository;
import persistence.repository.CustomerRepository;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MapperAccountService accountMapper;

    @Autowired
    private AccountGeneratorService accountNumberGenerator;

    public AccountDTO createAccount(AccountDTO account) {

        Optional<Customer> foundCustomer = customerRepository.findById(account.customerId());

        return foundCustomer.map(customer -> {
            Optional.ofNullable(getAccountByCustomerId(customer.getId())).ifPresent(
                    accountFound -> {
                        throw new BusinessException("Couldn't create the account",
                                "The customer already has an associated account.", "account");
                    });
            String accountNumber =
                    accountNumberGenerator.generateAccountNumberForCustomer(account.customerId());
            @Valid Account newAccount = accountMapper.mapAccountDTOToEntity(account, customer, accountNumber);
            accountRepository.save(newAccount);
            return accountMapper.mapAccountEntityToDTO(newAccount);

        }).orElseThrow(() ->
                new ElementNotFoundException("Customer not found. Couldn't create the account.", "customer"));
    }

    public AccountDTO getAccountByCustomerId(Integer customerId) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerId);
        foundCustomer.orElseThrow(
                () -> new ElementNotFoundException("The customer doesn't exist. Can't get the accounts.", "customer"));

        Optional<Account> foundAccount = accountRepository.findByCustomerId(customerId);
        return foundAccount.map(account -> accountMapper.mapAccountEntityToDTO(account)).orElse(null);
    }

}
