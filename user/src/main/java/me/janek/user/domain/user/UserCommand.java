package me.janek.user.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCommand {

  private final String email;
  private final String name;
  private final String password;
  private final String userToken;

  @Builder
  public UserCommand(String email, String name, String password, String userToken) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.userToken = userToken;
  }

  public User toEntity(String encryptedPassword) {
    return User.builder()
      .email(this.email)
      .name(this.name)
      .userToken(this.userToken)
      .encryptedPassword(encryptedPassword)
      .build();
  }

}
