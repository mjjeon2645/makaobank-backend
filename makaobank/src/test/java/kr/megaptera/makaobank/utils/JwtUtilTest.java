package kr.megaptera.makaobank.utils;

import kr.megaptera.makaobank.models.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
  static final private String SECRET = "SECRET";

  private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() {
    jwtUtil = new JwtUtil(SECRET);
  }

  @Test
  void encodeAndDecode() {
    AccountNumber original = new AccountNumber("1234");
    String token = jwtUtil.encode(original);

    assertThat(token).contains(".");

    AccountNumber accountNumber = jwtUtil.decode(token);

    assertThat(accountNumber).isEqualTo(original);
  }

  @Test
  void encodeError() {
   jwtUtil.decode("xxx");
  }
}
