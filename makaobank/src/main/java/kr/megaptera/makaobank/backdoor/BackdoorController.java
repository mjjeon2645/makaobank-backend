package kr.megaptera.makaobank.backdoor;

import org.springframework.jdbc.core.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  public BackdoorController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("setup-database")
  public String setupDatabase() {
    // 순서: 1. 기존 데이터 삭제. 2. 내가 원하는 데이터로 초기화

    LocalDateTime now = LocalDateTime.now();

    jdbcTemplate.execute("DELETE FROM account");

    jdbcTemplate.update("" +
            "INSERT INTO account(" +
            "id, name, account_number, amount, " +
                "created_at, updated_at)" +
            " VALUES(1, 'tester', '1234', 100000, ?, ?)",
        now, now
    );

    jdbcTemplate.update("" +
            "INSERT INTO account(" +
            "id, name, account_number, amount, " +
            "created_at, updated_at)" +
            " VALUES(2, 'ashal', '5678', 100, ?, ?)",
        now, now
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
