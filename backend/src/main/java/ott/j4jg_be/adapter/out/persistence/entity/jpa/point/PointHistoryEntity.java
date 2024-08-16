package ott.j4jg_be.adapter.out.persistence.entity.jpa.point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private Long id;
  private String userId;
  private long points;
  private String reason;
  private long finalBalance;
  @CreatedDate
  private LocalDateTime createdAt;
}
