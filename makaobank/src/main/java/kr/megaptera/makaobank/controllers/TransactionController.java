package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
  private final TransactionService transactionService;
  private final TransferService transferService;

  public TransactionController(TransactionService transactionService,
                               TransferService transferService) {
    this.transactionService = transactionService;
    this.transferService = transferService;
  }

  @GetMapping
  public TransactionsDto list() {
    //TODO. 인증 후 제대로 처리하자!
    AccountNumber accountNumber = new AccountNumber("1234");

    List<TransactionDto> transactionDtos =
        transactionService.list(accountNumber)
            .stream()
            .map(transaction -> transaction.toDto(accountNumber))
            .collect(Collectors.toList());

    return new TransactionsDto(transactionDtos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransferResultDto transfer(
      @Valid @RequestBody TransferDto transferDto
  ) {

    //TODO. 인증 후 제대로 처리하자!
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber(transferDto.getTo());

    Long amount = transferService.transfer(
        sender, receiver,
        transferDto.getAmount(), transferDto.getName());

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
