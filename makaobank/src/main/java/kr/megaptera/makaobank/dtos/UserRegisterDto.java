package kr.megaptera.makaobank.dtos;

import javax.validation.constraints.*;

public class UserRegisterDto {
  @NotBlank(message = "이름을 입력해주세요")
  @Size(min = 3, max = 7, message = "Invalid name length!")
  private String name;

//  @NotBlank(message = "계좌번호로 사용될 숫자를 입력해주세요(8글자)")
  private String accountNumber;

  @NotBlank(message = "비밀번호를 입력해주세요")
  private String password;

  @NotBlank(message = "비밀번호를 입력해주세요")
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
