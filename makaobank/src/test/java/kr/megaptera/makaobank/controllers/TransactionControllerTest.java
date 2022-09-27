package kr.megaptera.makaobank.controllers;

import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@ActiveProfiles("test")
class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TransactionService transactionService;

  @MockBean
  private TransferService transferService;

  @Test
  void list() throws Exception {
    Transaction transaction = mock(Transaction.class);

    AccountNumber accountNumber = new AccountNumber("1234");

    given(transactionService.list(accountNumber, 1))
        .willReturn(List.of(transaction));

    mockMvc.perform(MockMvcRequestBuilders.get("/transactions"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string(
            containsString("\"transactions\":[")
        ));

    verify(transactionService).list(accountNumber, 1);
  }

  @Test
  void transfer() throws Exception {
    AccountNumber sender = new AccountNumber("1234");
    AccountNumber receiver = new AccountNumber("5678");

    String name = "test";

    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"" + receiver.value() + "\"," +
                "\"amount\":100000," +
                "\"name\":\"" + name + "\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(transferService).transfer(sender, receiver, 100_000L, name);
  }

  @Test
  void transferWithIncorrectAmount() throws Exception {
    Long amount = 2_000_000L;

    given(transferService.transfer(any(), any(), any(), any()))
        .willThrow(new IncorrectAmount(amount));

    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"5678\"," +
                "\"amount\":" + amount + "," +
                "\"name\":\"test\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(status().isBadRequest()).andExpect(content().string(
            containsString("\"code\":1002")
        ));
  }

  @Test
  void transferWithIncorrectAccountNumber() throws Exception {
    AccountNumber accountNumber = new AccountNumber("2222");

    given(transferService.transfer(any(), any(), any(), any()))
        .willThrow(new AccountNotFound(accountNumber));

    mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"to\":\"" + accountNumber.value() + "\"," +
                "\"amount\":10000" + "," +
                "\"name\":\"test\"" +
                "}"))
        .andExpect(status().isBadRequest()).andExpect(content().string(
            containsString("\"code\":1001")
        ));
  }
}
