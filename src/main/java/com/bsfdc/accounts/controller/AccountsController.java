package com.bsfdc.accounts.controller;

import com.bsfdc.accounts.dto.AccountTransferRequestDto;
import com.bsfdc.accounts.dto.AccountsDto;
import com.bsfdc.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountsDto> getAccountDetails(@PathVariable(name = "id") String accountId) {
        return ResponseEntity.ok(accountsService.getAccountDetails(accountId));
    }

    @PostMapping("/transfer")
    public ResponseEntity<AccountsDto> transferAmount(@RequestBody @Valid AccountTransferRequestDto accountTransferRequest) {
        return ResponseEntity.ok(accountsService.transferAmount(accountTransferRequest));
    }
}
