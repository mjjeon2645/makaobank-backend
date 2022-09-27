package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;

@Entity
public class Account {
  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private AccountNumber accountNumber;

  private String name;

  private Long amount;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Account() {
  }

  public Account(AccountNumber accountNumber, String name) {
    this.accountNumber = accountNumber;
    this.name = name;
    this.amount = 0L;
  }

  public Account(Long id, AccountNumber accountNumber,
                 String name, Long amount) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.name = name;
    this.amount = amount;
  }

  public Long id() {
    return id;
  }

  public AccountNumber accountNumber() {
    return accountNumber;
  }

  public String name() {
    return name;
  }

  public Long amount() {
    return amount;
  }

  public static Account fake(String accountNumber) {
    return new Account(
        1L, new AccountNumber(accountNumber), "tester", 100_000L);
  }

  public AccountDto toDto() {
    return new AccountDto(accountNumber.value(), name, amount);
  }

  public void transfer(Account other, Long amount) {
    if (amount <= 0 || amount > this.amount) {
      throw new IncorrectAmount(amount);
    }

    this.amount -= amount;

    other.amount += amount;
  }
}
