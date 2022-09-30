package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.*;
import org.springframework.test.context.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TransactionRepositoryTest {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate ;

  @Test
  void save() {
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber("5678");
    Long amount = 100_000L;
    String name = "tester";

    Transaction transaction = new Transaction(
        sender, receiver, amount, name);

    transactionRepository.save(transaction);
  }

  @Test
  void findAllBySenderOrReceiver() {
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber("5678");
    Long amount = 100_000L;
    String name = "tester";

    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM transaction");

    jdbcTemplate.update("" +
            "INSERT INTO transaction(" +
            "id, sender, receiver, amount, name," +
            " created_at, updated_at" +
            ")" +
            " VALUES(1, ?, ?, ?, ?, ?, ?)",
        sender.value(), receiver.value(), amount, name, now, now
    );

    Sort sort = Sort.by("id").descending();
    Pageable pageable = PageRequest.of(0, 100, sort);
    List<Transaction> transactions =
        transactionRepository.findAllBySenderOrReceiver(
            sender, sender, pageable);

    assertThat(transactions).hasSize(1);

    assertThat(transactions.get(0).activity(sender)).isEqualTo("송금");
  }
}
