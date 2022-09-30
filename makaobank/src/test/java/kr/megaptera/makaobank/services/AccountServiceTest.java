package kr.megaptera.makaobank.services;

import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {
  private AccountService accountService;
  private AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    accountRepository = mock(AccountRepository.class);

    given(accountRepository.findByAccountNumber(any()))
        .willReturn(Optional.of(Account.fake("1234")));

    accountService = new AccountService(accountRepository);
  }

  @Test
  void account() {
    AccountNumber accountNumber = new AccountNumber("1234");

    Account account = accountService.detail(accountNumber);

    verify(accountRepository).findByAccountNumber(accountNumber);

    assertThat(account.accountNumber()).isEqualTo(accountNumber);
  }
}
