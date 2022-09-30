package kr.megaptera.makaobank.dtos;

public abstract class ErrorDto {
  private final int code;
  private final String message;

  public ErrorDto(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
