package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByAccountNumber(String accountNumber);

  Account save(Account account);
}
