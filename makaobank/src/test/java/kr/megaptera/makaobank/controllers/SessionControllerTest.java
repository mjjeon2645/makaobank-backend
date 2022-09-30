package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

  @BeforeEach
  void setUp() {

    AccountNumber accountNumber = new AccountNumber("1234");

    given(loginService.login(accountNumber, "password"))
        .willReturn(Account.fake(accountNumber));

    given(loginService.login(accountNumber, "xxx"))
        .willThrow(LoginFailed.class);
  }

  @Test
  void login() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"accountNumber\":\"1234\"," +
                "\"password\":\"password\"" +
                "}")
        )
        .andExpect(status().isCreated())
        .andExpect(content().string(
            containsString("\"amount\":")
        ));
  }

  @Test
  void loginFail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"accountNumber\":\"1234\"," +
                "\"password\":\"xxx\"" +
                "}")
        )
        .andExpect(status().isBadRequest());
  }
}
