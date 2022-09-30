package kr.megaptera.makaobank.exceptions;

public class TransferOneself extends RuntimeException {
  public TransferOneself() {
    super("본인의 계좌번호입니다. 다시 입력해주세요");
  }
}
