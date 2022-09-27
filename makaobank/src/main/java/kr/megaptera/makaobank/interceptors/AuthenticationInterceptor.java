package kr.megaptera.makaobank.interceptors;

import com.auth0.jwt.exceptions.*;
import kr.megaptera.makaobank.exceptions.*;
import kr.megaptera.makaobank.models.*;
import kr.megaptera.makaobank.utils.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.*;

public class AuthenticationInterceptor implements HandlerInterceptor {
  private final JwtUtil jwtUtil;

  public AuthenticationInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {

    String authorization = request.getHeader("Authorization");

    if(authorization == null || !authorization.startsWith("Bearer ")) {
      // 토큰 들어온 것이 없음(무작정 에러를 내면 안됨)
      return true;
    }

    String accessToken = authorization.substring("Bearer ".length());

    try {
      AccountNumber accountNumber = jwtUtil.decode(accessToken);
      request.setAttribute("accountNumber", accountNumber);
      return true;
    } catch (JWTDecodeException exception) {
      throw new AuthenticationError();
    }
  }
}
