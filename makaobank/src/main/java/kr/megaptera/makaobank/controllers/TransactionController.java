package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
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
  public TransactionsDto list(
      @RequestAttribute("accountNumber") AccountNumber accountNumber,
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    List<TransactionDto> transactionDtos =
        transactionService.list(accountNumber, page)
            .stream()
            .map(transaction -> transaction.toDto(accountNumber))
            .collect(Collectors.toList());

    return new TransactionsDto(transactionDtos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransferResultDto transfer(
      @RequestAttribute("accountNumber") AccountNumber sender,
      @Valid @RequestBody TransferDto transferDto
  ) {

    AccountNumber receiver = new AccountNumber(transferDto.getTo());

    Long amount = transferService.transfer(
        sender, receiver,
        transferDto.getAmount(), transferDto.getName());

    return new TransferResultDto(amount);
  }

  @ExceptionHandler(AccountNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto accountNotFound() {
    return new AccountNotFoundErrorDto();
  }

  @ExceptionHandler(IncorrectAmount.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto incorrectAmount() {
    return new IncorrectAmountErrorDto();
  }

  @ExceptionHandler(TransferOneself.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto transferOneselfError() {
    return new TransferOneselfErrorDto();
  }

  @ExceptionHandler(NegativeAmount.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto negativeAmountError() {
    return new NegativeAmountErrorDto();
  }
}
