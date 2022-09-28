package kr.megaptera.makaobank.exceptions;

public class RegisterFailedWithUnmatchedPasswords extends RuntimeException {
  public RegisterFailedWithUnmatchedPasswords() {
    super("비밀번호가 일치하지 않습니다");
  }
}
