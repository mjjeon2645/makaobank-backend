package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class AccountService {
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account detail(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new AccountNotFound(accountNumber));
  }
}
