package kr.megaptera.makaobank.utils;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import kr.megaptera.makaobank.models.*;

public class JwtUtil {
  private final Algorithm algorithm;

  public JwtUtil(String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  public String encode(AccountNumber accountNumber) {
    return JWT.create()
        .withClaim("accountNumber", accountNumber.value())
        .sign(algorithm);
  }

  public AccountNumber decode(String token) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT verify = verifier.verify(token);
    String value = verify.getClaim("accountNumber").asString();

    return new AccountNumber(value);
  }
}
