package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {
  @Autowired
  private AccountRepository accountRepository;

  @Test
  void create() {
    Account account = new Account("12345", "time tester");

    accountRepository.save(account);
  }
}
