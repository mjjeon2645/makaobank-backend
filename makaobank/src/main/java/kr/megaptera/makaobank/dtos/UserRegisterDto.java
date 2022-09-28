package kr.megaptera.makaobank.dtos;

import javax.validation.constraints.*;

public class UserRegisterDto {
  @Pattern(regexp = "/^[가-힣]{3,7}$/",
      message = "3~7자까지 한글만 사용 가능")
  private String name;

  @Pattern(regexp = "/^[0-9]{8}$/",
      message = "로그인 및 거래시 사용될 계좌번호이며 숫자만 사용 가능(8글자)")
  private String accountNumber;

  @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}",
      message = "8글자 이상의 영문(대소문자, 숫자, 특수문자가 모두 포함되어야 함")
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
