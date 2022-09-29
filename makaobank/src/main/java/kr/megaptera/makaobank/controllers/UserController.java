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
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public UserCreatedDto register(
      @Valid @RequestBody UserRegisterDto userRegisterDto
  ) {

    Account createdAccount = userService.create(userRegisterDto);

    return createdAccount.toUserCreatedDto();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String inValidRequest(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    for (ObjectError error : result.getAllErrors()) {

      System.out.println(error);
      System.out.println(error.getDefaultMessage());

      return error.getDefaultMessage();
    }
    return "Bad Request!";
  }

  @ExceptionHandler(RegisterFailedWithUnmatchedPasswords.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto registerFailedWithUnmatchedPasswords() {
    return new UnmatchedPasswordsErrorDto();
  }

  @ExceptionHandler(AccountNumberAlreadyExistedError.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto accountNumberAlreadyExisted() {
    return new AccountNumberAlreadyExistedErrorDto();
  }
}
