package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllBySenderOrReceiver(
      AccountNumber sender, AccountNumber receiver, Pageable pageable);

  Transaction save(Transaction transaction);
}
