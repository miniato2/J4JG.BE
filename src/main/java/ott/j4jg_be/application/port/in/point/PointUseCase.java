package ott.j4jg_be.application.port.in.point;

public interface PointUseCase {
  void usePoints(String userId, int points, String reason);
  void refundPoints(String userId, int points, String reason);
  void addPoints(String userId, int points, String reason);
}
