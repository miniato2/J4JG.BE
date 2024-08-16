package ott.j4jg_be.domain.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Point {
  private String userId;
  private long balance;
  private PointHistory currentHistory;

  public Point(String userId, long balance) {
    this.userId = userId;
    this.balance = balance;
  }

  public void usePoints(long points, String reason) {
    if (points <= 0) {
      throw new IllegalArgumentException("Points must be positive");
    }
    if (this.balance < points) {
      throw new IllegalArgumentException("Not enough points");
    }
    this.balance -= points;
    this.currentHistory = PointHistory.createHistory(points, reason, this.balance);
  }

  public void refundPoints(long points, String reason) {
    if (points <= 0) {
      throw new IllegalArgumentException("Points must be positive");
    }
    this.balance += points;
    this.currentHistory = PointHistory.createHistory(points, reason, this.balance);
  }

  public void addPoints(long points, String reason) {
    if (points <= 0) {
      throw new IllegalArgumentException("Points must be positive");
    }
    this.balance += points;
    this.currentHistory = PointHistory.createHistory(points, reason, this.balance);
  }
}

