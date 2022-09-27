package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class LoginService {

  public Account login(String accountNumber, String password) {
    if (!password.equals("password")) {
      throw new LoginFailed();
    }

    Account account = Account.fake(accountNumber);
    return account;
  }
}
