package kr.megaptera.makaobank.models;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
  @Test
  void transfer() {
    Long amount1 = 100_000L;
    Long amount2 = 500L;
    Long transferAmount = 1_000L;

    Account account1 = new Account(1L, "1234", "tester", amount1);
    Account account2 = new Account(2L, "5678", "ashal", amount2);

    account1.transfer(account2, transferAmount);

    assertThat(account1.amount()).isEqualTo(amount1 - transferAmount);
    assertThat(account2.amount()).isEqualTo(amount2 + transferAmount);
  }
}
