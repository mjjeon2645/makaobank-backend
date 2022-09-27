package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.exceptions.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  final Long AMOUNT1 = 100_000L;
  final Long AMOUNT2 = 500L;

  private Account account1;
  private Account account2;

  @BeforeEach
  void setUp() {
    AccountNumber accountNumber1 = new AccountNumber("1234");
    AccountNumber accountNumber2 = new AccountNumber("5678");
    account1 = new Account(1L, accountNumber1, "tester", AMOUNT1);
    account2 = new Account(2L, accountNumber2, "ashal", AMOUNT2);
  }

  @Test
  void transfer() {
    final Long transferAmount = 1_000L;
    account1.transfer(account2, transferAmount);

    assertThat(account1.amount()).isEqualTo(AMOUNT1 - transferAmount);
    assertThat(account2.amount()).isEqualTo(AMOUNT2 + transferAmount);
  }

  @Test
  void transferWithNegativeAmount() {
    final Long transferAmount = -500_000L;

    assertThrows(IncorrectAmount.class, () -> {
      account1.transfer(account2, transferAmount);
    });
  }

  @Test
  void transferWithTooLargeAmount() {
    final Long transferAmount = AMOUNT1 + 500_000L;

    assertThrows(IncorrectAmount.class, () -> {
      account1.transfer(account2, transferAmount);
    });
  }
}
