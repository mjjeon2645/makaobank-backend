package kr.megaptera.makaobank.models;

import kr.megaptera.makaobank.dtos.*;

import javax.persistence.*;

@Entity
public class Account {
  @Id
  @GeneratedValue
  private Long id;

  private String accountNumber;

  private String name;

  private Long amount;

  public Account() {
  }

  public Account(Long id, String accountNumber, String name, Long amount) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.name = name;
    this.amount = amount;
  }

  public Long id() {
    return id;
  }

  public String accountNumber() {
    return accountNumber;
  }

  public String name() {
    return name;
  }

  public Long amount() {
    return amount;
  }

  public static Account fake(String accountNumber) {
    return new Account(1L, accountNumber, "tester", 100_000L);
  }

  public AccountDto toDto() {
    return new AccountDto(accountNumber, name, amount);
  }
}
