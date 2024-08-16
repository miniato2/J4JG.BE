package ott.j4jg_be.domain.point;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory {
  private long points;
  private String reason;
  private LocalDateTime createdAt;
  private long finalBalance;

  public static PointHistory createHistory(long points, String reason, long finalBalance) {
    return PointHistory.builder()
        .points(points)
        .reason(reason)
        .finalBalance(finalBalance)
        .createdAt(LocalDateTime.now())
        .build();
  }
}
