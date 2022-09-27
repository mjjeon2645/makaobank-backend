package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;

@Entity
public class Transaction {
  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "sender"))
  private AccountNumber sender;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "receiver"))
  private AccountNumber receiver;

  private Long amount;

  private String name;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Transaction() {
  }

  public Transaction(
      AccountNumber sender, AccountNumber receiver,
      Long amount, String name) {
    this.sender = sender;
    this.receiver = receiver;
    this.amount = amount;
    this.name = name;
  }

  public TransactionDto toDto(AccountNumber currentAccountNumber) {
    return new TransactionDto(
        id,
        activity(currentAccountNumber),
        name(currentAccountNumber),
        amount);
  }

  public String activity(AccountNumber currentAccountNumber) {
    return currentAccountNumber.equals(sender) ? "송금" : "입금";
  }

  public String name(AccountNumber currentAccountNumber) {
    return currentAccountNumber.equals(sender) ? receiver.value() : name;
  }
}
