package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> list(AccountNumber accountNumber) {
    List<Transaction> transactions =
        transactionRepository
            .findAllBySenderOrReceiverOrderByCreatedAtDesc(
                accountNumber, accountNumber);
    return transactions;
  }
}
