package web.controller;

import common.DTO.AccountDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.account.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid final AccountDTO account) {
        AccountDTO createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping
    @Validated
    public ResponseEntity<?> getAccountByCustomerId(@RequestParam @NotNull final Integer customerId) {
        try {
            AccountDTO customerAccount = accountService.getAccountByCustomerId(customerId);
            return new ResponseEntity<>(Optional.of(customerAccount), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

    //@GetMapping
    //public ResponseEntity<?> getAllAccounts() {
    //    try {
    //        List<AccountDTO> accounts = accountService.getAllAccounts();
    //        return new ResponseEntity<>(accounts, HttpStatus.ACCEPTED);
    //    } catch (Exception ex) {
    //        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    //    }
    //}
}
