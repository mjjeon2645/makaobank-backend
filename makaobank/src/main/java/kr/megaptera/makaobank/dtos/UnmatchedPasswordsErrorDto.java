package kr.megaptera.makaobank.dtos;

public class UnmatchedPasswordsErrorDto extends ErrorDto {
  public UnmatchedPasswordsErrorDto() {
    super(2001, "비밀번호가 일치하지 않습니다");
  }
}
