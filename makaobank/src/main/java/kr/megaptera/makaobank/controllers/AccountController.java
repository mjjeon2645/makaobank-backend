package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("me")
  public AccountDto account(
      @RequestAttribute("accountNumber") AccountNumber accountNumber
  ) {

    System.out.println("*".repeat(20));
    System.out.println(accountNumber);

    Account account = accountService.detail(accountNumber);
    return account.toDto();
  }

  @ExceptionHandler(AccountNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String accountNotFound() {
    return "Account not found!";
  }
}
