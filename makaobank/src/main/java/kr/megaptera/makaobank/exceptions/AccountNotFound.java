package kr.megaptera.makaobank.exceptions;

import kr.megaptera.makaobank.models.*;

public class AccountNotFound extends RuntimeException{
  public AccountNotFound(AccountNumber accountNumber) {
    super("Account Not Found: " + accountNumber);
  }
}
