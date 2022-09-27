package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

// 이걸로 래핑해도 문제 없는지 살펴보기
@SuppressWarnings("unchecked")
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByAccountNumber(String accountNumber);

  Account save(Account account);
}
