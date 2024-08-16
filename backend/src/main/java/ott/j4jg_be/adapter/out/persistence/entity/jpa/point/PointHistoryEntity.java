package ott.j4jg_be.adapter.out.persistence.entity.jpa.point;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "point_id")
  private Long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "points")
  private long points;

  @Column(name = "reason")
  private String reason;

  @Column(name = "final_balance")
  private long finalBalance;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
