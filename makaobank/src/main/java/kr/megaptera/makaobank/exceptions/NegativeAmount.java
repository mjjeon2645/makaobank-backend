package kr.megaptera.makaobank.exceptions;

public class NegativeAmount extends RuntimeException {
  public NegativeAmount() {
    super("보낼 금액을 정확히 입력해주세요(음수 입력 불가)");
  }
}
