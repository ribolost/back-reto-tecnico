package service.account;

import common.DTO.AccountDTO;
import org.springframework.stereotype.Service;
import persistence.model.Account;
import persistence.model.Customer;

@Service
public class MapperAccountService {

    public AccountDTO mapAccountEntityToDTO(Account account) {
        return new AccountDTO(account.getId(), account.getCustomer().getId(), account.getAccountNumber(),
                account.getStatus());
    }

    public Account mapAccountDTOToEntity(AccountDTO account, Customer customer, String accountNumber) {
        return Account.builder()
                .customer(customer)
                .accountNumber(accountNumber)
                .status(account.status())
                .build();
    }

}
