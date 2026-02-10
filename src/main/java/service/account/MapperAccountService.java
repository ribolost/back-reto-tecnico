package service.account;

import common.DTO.AccountDTO;
import org.springframework.stereotype.Service;
import persistence.model.Account;

@Service
public class MapperAccountService {

    public AccountDTO mapAccountEntityToDTO(Account account) {
        return new AccountDTO(account.getId(), account.getCustomerId(), account.getAccountNumber(),
                account.getStatus());
    }

    public Account mapAccountDTOToEntity(AccountDTO account) {
        return Account.builder()
                .customerId(account.customerId())
                .accountNumber(account.accountNumber())
                .status(account.status())
                .build();
    }

    public Account mapAccountDTOToEntity(AccountDTO account, String accountNumber) {
        return Account.builder()
                .customerId(account.customerId())
                .accountNumber(accountNumber)
                .status(account.status())
                .build();
    }

}
