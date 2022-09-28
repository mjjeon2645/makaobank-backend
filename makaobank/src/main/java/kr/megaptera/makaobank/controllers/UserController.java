package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.springframework.http.*;
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

  @ExceptionHandler(RegisterFailedWithUnmatchedPasswords.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto registerFailedWithUnmatchedPasswords() {
    return new UnmatchedPasswordsErrorDto();
  }
}
