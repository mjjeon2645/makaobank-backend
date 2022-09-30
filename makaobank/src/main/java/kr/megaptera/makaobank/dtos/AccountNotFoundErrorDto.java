package kr.megaptera.makaobank.dtos;

public class AccountNotFoundErrorDto extends ErrorDto {
  public AccountNotFoundErrorDto() {
    super(1001, "잘못된 계좌번호입니다. 다시 입력해주세요");
  }
}
