package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.repositories.*;
import kr.megaptera.makaobank.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private UserService userService;

  @MockBean
  private AccountRepository accountRepository;

  @Test
  void register() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"minji\"," +
                "\"accountNumber\":\"11223344\"," +
                "\"password\":\"abcdABCD1!\"," +
                "\"checkPassword\":\"abcdABCD1!\"" +
                "}"))
        .andExpect(content().string(
            containsString("minji")))
        .andExpect(status().isCreated());

    verify(accountRepository).save(any());
  }
}
