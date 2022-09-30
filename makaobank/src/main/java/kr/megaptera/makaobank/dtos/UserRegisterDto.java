package kr.megaptera.makaobank.dtos;

import javax.validation.constraints.*;

public class UserRegisterDto {

  private String name;

  private String accountNumber;

  private String password;

  private String checkPassword;

  public UserRegisterDto() {
  }

  public UserRegisterDto(String name, String accountNumber,
                         String password, String checkPassword) {
    this.name = name;
    this.accountNumber = accountNumber;
    this.password = password;
    this.checkPassword = checkPassword;
  }

  public String getName() {
    return name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getPassword() {
    return password;
  }

  public String getCheckPassword() {
    return checkPassword;
  }
}
