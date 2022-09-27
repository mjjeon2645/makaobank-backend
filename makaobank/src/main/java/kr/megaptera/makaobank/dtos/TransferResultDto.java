package kr.megaptera.makaobank.dtos;

public class TransferResultDto {
  private Long transferred;

  public TransferResultDto() {
  }

  public TransferResultDto(Long transferred) {
    this.transferred = transferred;
  }

  public Long getTransferred() {
    return transferred;
  }
}
