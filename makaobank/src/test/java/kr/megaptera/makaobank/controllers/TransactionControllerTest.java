package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@ActiveProfiles("test")
class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TransferService transferService;

  @Test
  void transfer() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"5678\"," +
                "\"amount\":\"1000\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(transferService).transfer("1234", "5678", 1_000L);
  }
  @Test
  void transferWithIncorrectAmount() throws Exception {
    Long amount = 2_000_000L;

    given(transferService.transfer(any(), any(), any()))
        .willThrow(new IncorrectAmount(amount));

    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"5678\"," +
                "\"amount\":" + amount +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(status().isBadRequest()).andExpect(content().string(
            containsString("\"code\":1002")
        ));
  }

  @Test
  void transferWithIncorrectAccountNumber() throws Exception {
    String accountNumber = "2222";

    given(transferService.transfer(any(), any(), any()))
        .willThrow(new AccountNotFound(accountNumber));

    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"" + accountNumber + "\"," +
                "\"amount\":10000" +
                "}"))
        .andExpect(status().isBadRequest()).andExpect(content().string(
            containsString("\"code\":1001")
        ));
  }
}
