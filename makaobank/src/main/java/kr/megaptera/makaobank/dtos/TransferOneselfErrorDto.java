package kr.megaptera.makaobank.dtos;

public class TransferOneselfErrorDto extends ErrorDto {
  public TransferOneselfErrorDto() {
    super(1003, "본인의 계좌번호입니다. 다시 입력해주세요");
  }
}
