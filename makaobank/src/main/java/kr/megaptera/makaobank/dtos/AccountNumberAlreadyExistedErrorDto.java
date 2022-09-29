package kr.megaptera.makaobank.dtos;

public class AccountNumberAlreadyExistedErrorDto extends ErrorDto {
  public AccountNumberAlreadyExistedErrorDto() {
    super(2002, "이미 존재하는 계좌번호입니다. 다른 계좌번호를 입력해주세요");
  }
}
