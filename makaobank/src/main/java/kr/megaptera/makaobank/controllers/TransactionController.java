package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
  private final TransferService transferService;

  public TransactionController(TransferService transferService) {
    this.transferService = transferService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public TransferResultDto transfer(
      @Valid @RequestBody TransferDto transferDto
  ) {

    //TODO. 인증 후 제대로 처리
    String accountNumber = "1234";

    Long amount = transferService
        .transfer(
            accountNumber,
            transferDto.getTo(),
            transferDto.getAmount());

    return new TransferResultDto(amount);
  }

  @ExceptionHandler(AccountNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  // 에러 DTO를 사용 =>
  public ErrorDto accountNotFound() {
    return new AccountNotFoundErrorDto();
  }

  @ExceptionHandler(IncorrectAmount.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  // 에러 DTO를 사용 =>
  public ErrorDto incorrectAmount() {
    return new IncorrectAmountErrorDto();
  }

}
