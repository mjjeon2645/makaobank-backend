package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  // 글 리스트가 100개까지 출력되고 이후에 페이지가 넘어감(명령어 실행 시 쌍따옴표 붙여줘야 가능 ? 이거때문에
  public List<Transaction> list(AccountNumber accountNumber, int page) {
    Sort sort = Sort.by("createdAt").descending();
    Pageable pageable = PageRequest.of(page - 1, 100, sort);
    return transactionRepository
        .findAllBySenderOrReceiver(
            accountNumber, accountNumber, pageable);
  }
}
