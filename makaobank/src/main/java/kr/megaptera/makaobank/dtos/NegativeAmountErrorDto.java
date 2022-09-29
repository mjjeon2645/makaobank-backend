package kr.megaptera.makaobank.dtos;

public class NegativeAmountErrorDto extends ErrorDto {
  public NegativeAmountErrorDto() {
    super(2002, "보낼 금액을 정확히 입력해주세요(음수 입력 불가)");
  }
}
