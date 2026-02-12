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
    public ResponseEntity<?> getAccountByCustomerId(@RequestParam("customerId") @NotNull final Integer customerId) {
        AccountDTO customerAccount = accountService.getAccountByCustomerId(customerId);
        return new ResponseEntity<>(Optional.ofNullable(customerAccount), HttpStatus.OK);
    }

}
