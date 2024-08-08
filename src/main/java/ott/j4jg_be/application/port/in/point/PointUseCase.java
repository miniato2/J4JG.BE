package ott.j4jg_be.application.port.in.point;

public interface PointUseCase {
  void usePoints(Long userId, int points, String reason);
  void refundPoints(Long userId, int points, String reason);
  void addPoints(Long userId, int points, String reason);
}
