package kr.megaptera.makaobank.exceptions;

public class AccountNumberAlreadyExistedError extends RuntimeException {
  public AccountNumberAlreadyExistedError() {
    super("이미 존재하는 계좌번호입니다. 다른 계좌번호를 입력해주세요");
  }
}
