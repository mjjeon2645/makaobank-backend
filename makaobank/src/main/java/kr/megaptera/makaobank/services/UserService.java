package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class UserService {
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder ;

  public UserService(AccountRepository accountRepository,
                     PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Account create(UserRegisterDto userRegisterDto) {
    // 1. 가져온 데이터 정리
    String name = userRegisterDto.getName();
    AccountNumber accountNumber
        = new AccountNumber(userRegisterDto.getAccountNumber());
    String password = userRegisterDto.getPassword();
    String checkPassword = userRegisterDto.getCheckPassword();

    // 비밀번호 - 확인 비밀번호 불일치
    if (!Objects.equals(password, checkPassword)) {
      throw new RegisterFailedWithUnmatchedPasswords();
    }

    // 입력한 어카운트 넘버가 이미 존재하고 있을 때
    Account found = accountRepository.findByAccountNumber(accountNumber)
        .orElse(null);

    if (found != null) {
      throw new AccountNumberAlreadyExistedError();
    }

    // 2. 어카운트 생성
    Account account = new Account(null, accountNumber, name);

    account.changePassword(password, passwordEncoder);

    accountRepository.save(account);

   // 3. 해당 어카운트를 반환
    return account;
  }
}
