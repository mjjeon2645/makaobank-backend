package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.transaction.annotation.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TransactionRepositoryTest {

  @Autowired
  private TransactionRepository transactionRepository;

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
}
