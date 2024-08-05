package ott.j4jg_be.domain.user;

import lombok.Getter;
import lombok.Setter;
import ott.j4jg_be.domain.point.Point;

@Getter
@Setter
public class User {
  private Long id;
  private String username;
  private String email;
  private Point point;
}
