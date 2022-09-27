package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import kr.megaptera.makaobank.utils.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("session")
public class SessionController {
  private final LoginService loginService;
  private final JwtUtil jwtUtil;

  public SessionController(LoginService loginService, JwtUtil jwtUtil) {
    this.loginService = loginService;
    this.jwtUtil = jwtUtil;
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

    String accessToken = jwtUtil.encode(accountNumber);

    return new LoginResultDto(
        accessToken, account.name(), account.amount()
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
