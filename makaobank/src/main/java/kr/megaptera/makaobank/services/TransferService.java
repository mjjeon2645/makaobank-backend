package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class TransferService {
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  public TransferService(AccountRepository accountRepository,
                         TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  public Long transfer(AccountNumber from, AccountNumber to,
                       Long amount, String name) {
    Account account1 = accountRepository.findByAccountNumber(from)
        .orElseThrow(() -> new AccountNotFound(from));
    Account account2 = accountRepository.findByAccountNumber(to)
        .orElseThrow(() -> new AccountNotFound(to));

    if (account1.accountNumber().value().equals(account2.accountNumber().value())) {
      throw new TransferOneself();
    }

    account1.transfer(account2, amount);

    Transaction transaction = new Transaction(
        account1.accountNumber(), account2.accountNumber(), amount, name
    );

    transactionRepository.save(transaction);

    return amount;
  }
}
