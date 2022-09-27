package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("session")
public class SessionController {
  private LoginService loginService;

  public SessionController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(
      @Valid @RequestBody LoginRequestDto loginRequestDto
  ) {
    AccountNumber accountNumber =
        new AccountNumber(loginRequestDto.getAccountNumber());

    String password = loginRequestDto.getPassword();

    Account account = loginService.login(accountNumber, password);

    return new LoginResultDto(
        account.accountNumber().value(),
        account.name(),
        account.amount()
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String inValidRequest(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    for (ObjectError error : result.getAllErrors()) {
      return error.getDefaultMessage();
    }
    return "Bad Request!";
  }

  @ExceptionHandler(LoginFailed.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() {
    return "아이디 혹은 비밀번호가 맞지 않습니다";
  }
}
