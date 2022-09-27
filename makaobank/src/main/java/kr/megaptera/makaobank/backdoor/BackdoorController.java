package kr.megaptera.makaobank.backdoor;

import org.springframework.jdbc.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  private final PasswordEncoder passwordEncoder;

  public BackdoorController(JdbcTemplate jdbcTemplate,
                            PasswordEncoder passwordEncoder) {
    this.jdbcTemplate = jdbcTemplate;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("setup-database")
  public String setupDatabase() {
    // 순서: 1. 기존 데이터 삭제. 2. 내가 원하는 데이터로 초기화

    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM transaction");
    jdbcTemplate.execute("DELETE FROM account");

    jdbcTemplate.update("" +
            "INSERT INTO account(" +
            "id, account_number, encoded_password, name, " +
            "amount, created_at, updated_at)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?)",
        "1234", passwordEncoder.encode("password"), "tester",
        1_000_000, now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO account(" +
            "id, account_number, encoded_password, name, " +
            "amount, created_at, updated_at)" +
            " VALUES(2, ?, ?, ?, ?, ?, ?)",
        "5678", passwordEncoder.encode("password"), "ashal",
        100_000, now, now
    );

    return "ok";
  }

  @GetMapping("change-amount")
  public String changeAmount(
      @RequestParam Long userId,
      @RequestParam Long amount
  ) {

    // 잔액 변경 방법
    jdbcTemplate.update("UPDATE account SET amount=? WHERE id=?",
        amount, userId);

    return "ok";
  }
}
