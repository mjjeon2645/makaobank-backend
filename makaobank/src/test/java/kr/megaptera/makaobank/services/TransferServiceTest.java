package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class TransferServiceTest {
  TransferService transferService;

  AccountRepository accountRepository;

  TransactionRepository transactionRepository;

  @BeforeEach
  void setUp() {
    accountRepository = mock(AccountRepository.class);
    transactionRepository = mock(TransactionRepository.class);

    transferService = new TransferService(
        accountRepository, transactionRepository);
  }

  @Test
  void transfer() {
    // 시나리오
    Long amount1 = 100_000L;
    Long amount2 = 500L;
    Long transferAmount = 1_000L;
    String name = "tester";

    AccountNumber accountNumber1 = new AccountNumber("1234");
    AccountNumber accountNumber2 = new AccountNumber("5678");

    Account account1 = new Account(1L, accountNumber1, "tester", amount1);
    Account account2 = new Account(2L, accountNumber2, "ashal", amount2);

    given(accountRepository.findByAccountNumber(account1.accountNumber()))
        .willReturn(Optional.of(account1));
    given(accountRepository.findByAccountNumber(account2.accountNumber()))
        .willReturn(Optional.of(account2));

    transferService.transfer(
        accountNumber1, accountNumber2, transferAmount, name);

    assertThat(account2.amount()).isEqualTo(amount2 + transferAmount);
    assertThat(account1.amount()).isEqualTo(amount1 - transferAmount);

    verify(transactionRepository).save(any());
  }

  @Test
  void transferWithIncorrectToAccountNumber() {
    AccountNumber accountNumber1 = new AccountNumber("1234");
    AccountNumber accountNumber2 = new AccountNumber("5678");
    Long transferAmount = 1_000L;
    String name = "tester";

    Account account = new Account(1L, accountNumber1, name, 100_000L);

    given(accountRepository.findByAccountNumber(account.accountNumber()))
        .willReturn(Optional.of(account));

    assertThrows(AccountNotFound.class, () -> {
      transferService.transfer(
          accountNumber1, accountNumber2, transferAmount, name);
    });
  }

  @Test
  void transferWithIncorrectFromAccountNumber() {
    AccountNumber accountNumber1 = new AccountNumber("1234");
    AccountNumber accountNumber2 = new AccountNumber("5678");
    Long transferAmount = 1_000L;
    String name = "tester";

    Account account2 = new Account(2L, accountNumber2, "ashal", 100L);

    given(accountRepository.findByAccountNumber(account2.accountNumber()))
        .willReturn(Optional.of(account2));

    assertThrows(AccountNotFound.class, () -> {
      transferService.transfer(
          accountNumber1, accountNumber2, transferAmount, name);
    });
  }
}
