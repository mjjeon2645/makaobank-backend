package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
  private LoginService loginService;

  @BeforeEach
  void setUp() {
    loginService = new LoginService();
  }

  @Test
  void loginSuccess() {
        Account account = loginService.login("1234", "password");

        assertThat(account.accountNumber())
            .isEqualTo(new AccountNumber("1234"));
  }

  @Test
  void loginFail() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("1234", "xxx");
    });
  }
}
