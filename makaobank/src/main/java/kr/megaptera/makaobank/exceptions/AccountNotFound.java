package kr.megaptera.makaobank.exceptions;

import kr.megaptera.makaobank.models.*;

// accountNumber.toString() 아닌가?
public class AccountNotFound extends RuntimeException{
  public AccountNotFound(AccountNumber accountNumber) {
    super("Account Not Found: " + accountNumber);
  }
}
