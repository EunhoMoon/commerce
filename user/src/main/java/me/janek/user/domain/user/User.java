package me.janek.user.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, unique = true)
  private String userToken;

  @Column(nullable = false)
  private String encryptedPassword;

  @Builder
  public User(String email, String name, String userToken, String encryptedPassword) {
    this.email = email;
    this.name = name;
    this.userToken = userToken;
    this.encryptedPassword = encryptedPassword;
  }

}
