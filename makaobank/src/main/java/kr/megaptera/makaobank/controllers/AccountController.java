package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {
  @GetMapping("me")
  public AccountDto account() {
    return new AccountDto("1234", "tester", 100_000L);
  }
}
