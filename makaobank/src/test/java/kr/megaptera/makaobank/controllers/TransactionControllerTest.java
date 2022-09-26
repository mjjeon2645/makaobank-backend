package kr.megaptera.makaobank.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.test.web.servlet.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void transfer() {

  }
}