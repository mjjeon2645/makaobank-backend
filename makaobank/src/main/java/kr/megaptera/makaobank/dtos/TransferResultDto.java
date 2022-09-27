package kr.megaptera.makaobank.dtos;

public class TransferResultDto {
  private Long amount;

  public TransferResultDto() {
  }

  public TransferResultDto(Long amount) {
    this.amount = amount;
  }

  public Long getAmount() {
    return amount;
  }
}
