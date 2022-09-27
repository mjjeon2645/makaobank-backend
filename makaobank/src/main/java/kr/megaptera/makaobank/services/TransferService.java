package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class TransferService {
  private AccountRepository accountRepository;

  public TransferService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Long transfer(String from, String to, Long amount) {
    Account account1 = accountRepository.findByAccountNumber(from)
        .orElseThrow(() -> new AccountNotFound(from));
    Account account2 = accountRepository.findByAccountNumber(to)
        .orElseThrow(() -> new AccountNotFound(to));

    account1.transfer(account2, amount);

    return amount;
  }
}
