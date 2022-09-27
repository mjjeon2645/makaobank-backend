package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
  private TransactionService transactionService;
  private TransactionRepository transactionRepository;

  @BeforeEach
  void setUp() {
    transactionRepository = mock(TransactionRepository.class);
    transactionService = new TransactionService(transactionRepository);
  }

  @Test
  void list() {
    AccountNumber accountNumber = new AccountNumber("1234");

    Transaction transaction = mock(Transaction.class);

    given(transactionRepository
        .findAllBySenderOrReceiver(accountNumber, accountNumber, any()))
        .willReturn(List.of(transaction));

    List<Transaction> transactions =
        transactionService.list(accountNumber, 1);

    assertThat(transactions).hasSize(1);
  }
}
