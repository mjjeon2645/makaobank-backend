package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.dtos.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UserServiceTest {
  private UserService userService;

  private AccountRepository accountRepository;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setUp() {
    accountRepository = mock(AccountRepository.class);
    passwordEncoder = new Argon2PasswordEncoder();
    userService = new UserService(accountRepository, passwordEncoder);
  }

  @Test
  void create() {
    UserRegisterDto userRegisterDto =
        new UserRegisterDto("minji", "11223344", "abcdABCD1!", "abcdABCD1!");

    Account newAccount = userService.create(userRegisterDto);

    assertThat(newAccount.name()).isEqualTo("minji");
    assertThat(newAccount.accountNumber().value()).isEqualTo("11223344");
  }
}
