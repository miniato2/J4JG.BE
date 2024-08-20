package ott.j4jg_be.domain.user;

import lombok.Getter;
import lombok.Setter;
import ott.j4jg_be.common.e_num.USERROLE;

@Getter
@Setter
public class User {
  private String id;
  private String email;
  private String phone;
  private USERROLE role;
  private UserInfo userInfo;

}
