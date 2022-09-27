package kr.megaptera.makaobank.models;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class TransactionTest {
  @Test
  void activity() {
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber("5678");

    Transaction transaction = new Transaction(
        sender, receiver, 100_000L, "tester"
    );

    assertThat(transaction.activity(sender)).isEqualTo("송금");
    assertThat(transaction.activity(receiver)).isEqualTo("입금");
  }

  @Test
  void name() {
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber("5678");

    Transaction transaction = new Transaction(
        sender, receiver, 100_000L, "tester"
    );

    assertThat(transaction.name(sender)).isEqualTo("5678");
    assertThat(transaction.name(receiver)).isEqualTo("tester");
  }
}
