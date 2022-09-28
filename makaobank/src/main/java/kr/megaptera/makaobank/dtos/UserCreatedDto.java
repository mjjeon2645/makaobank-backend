package kr.megaptera.makaobank.dtos;

public class UserCreatedDto {
  private String userName;
  private String userAccountNumber;
  private Long userAmount;

  public UserCreatedDto() {
  }

  public UserCreatedDto(String name, String accountNumber, Long amount) {
    this.userName = name;
    this.userAccountNumber = accountNumber;
    this.userAmount = amount;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserAccountNumber() {
    return userAccountNumber;
  }

  public Long getUserAmount() {
    return userAmount;
  }
}
