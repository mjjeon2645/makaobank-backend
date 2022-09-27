package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TransferServiceTest {
  private TransferService transferService;
  private AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    accountRepository = mock(AccountRepository.class);

    transferService = new TransferService(accountRepository);
  }

  @Test
  void transfer() {
    // 시나리오
    Long amount1 = 100_000L;
    Long amount2 = 500L;
    Long transferAmount = 1_000L;

    Account account1 = new Account(1L, "1234", "tester", amount1);
    Account account2 = new Account(2L, "5678", "ashal", amount2);

    given(accountRepository.findByAccountNumber(account1.accountNumber()))
        .willReturn(Optional.of(account1));
    given(accountRepository.findByAccountNumber(account2.accountNumber()))
        .willReturn(Optional.of(account2));

    transferService.transfer("1234", "5678", transferAmount);

    assertThat(account2.amount()).isEqualTo(amount2 + transferAmount);
    assertThat(account1.amount()).isEqualTo(amount1 - transferAmount);
  }
}
