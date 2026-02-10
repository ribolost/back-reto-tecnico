package service.account;

import common.DTO.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.model.Account;
import persistence.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MapperAccountService accountMapper;

    @Autowired
    private IBANAccountGeneratorService accountNumberGenerator;

    public AccountDTO createAccount(AccountDTO account) {
        String accountNumber = accountNumberGenerator.generateAccountNumberForCustomer(account.customerId());
        Account newAccount = accountMapper.mapAccountDTOToEntity(account, accountNumber);
        accountRepository.save(newAccount);
        return accountMapper.mapAccountEntityToDTO(newAccount);
    }

    public AccountDTO getAccountByCustomerId(Integer customerId) {
        Optional<Account> foundAccount = accountRepository.findByCustomerId(customerId);
        return foundAccount.map(account -> accountMapper.mapAccountEntityToDTO(account)).orElse(null);
    }

}
