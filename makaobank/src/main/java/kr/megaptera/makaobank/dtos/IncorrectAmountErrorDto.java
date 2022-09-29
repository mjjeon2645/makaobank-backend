package kr.megaptera.makaobank.dtos;

public class IncorrectAmountErrorDto extends ErrorDto {
  public IncorrectAmountErrorDto() {
    super(2001, "계좌 잔액이 부족합니다");
  }
}
