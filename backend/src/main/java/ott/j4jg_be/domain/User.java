package ott.j4jg_be.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
  private Long id;
  private String username;
  private String email;
  private Point point;
}
