package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class LoginService {
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginService(AccountRepository accountRepository,
                      PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Account login(AccountNumber accountNumber, String password) {
    Account account = accountRepository.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new LoginFailed());

    if (!account.authenticate(password, passwordEncoder)) {
      throw new LoginFailed();
    }

    return account;
  }
}
