package kr.megaptera.makaobank.repositories;

import kr.megaptera.makaobank.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllBySenderOrReceiverOrderByCreatedAtDesc(
      AccountNumber sender, AccountNumber receiver);

  Transaction save(Transaction transaction);
}
