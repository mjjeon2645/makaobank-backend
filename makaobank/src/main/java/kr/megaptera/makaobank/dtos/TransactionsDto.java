package kr.megaptera.makaobank.dtos;

import java.util.*;

public class TransactionsDto {
  private final List<TransactionDto> transactions;

  public TransactionsDto(List<TransactionDto> transactions) {
    this.transactions = transactions;
  }

  public List<TransactionDto> getTransactions() {
    // 원래는 내부에 있는 것 그대로 주면 안되나 DTO 특성 상 괜찮다고 봄
    return transactions;
  }
}
