package kr.megaptera.makaobank.advices;

import kr.megaptera.makaobank.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class AuthenticationErrorAdvice {
  @ResponseBody
  @ExceptionHandler(AuthenticationError.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String authenticationError() {
    return "Authentication Error!";
  }
}
