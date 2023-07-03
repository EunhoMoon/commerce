package me.janek.user.interfaces.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import me.janek.user.domain.user.UserCommand;
import me.janek.user.domain.user.UserInfo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static me.janek.user.interfaces.user.OrderDto.*;

@Data
public class UserDto {

  @Data
  public static class RegisterRequest {

    @Email
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String password;

    public UserCommand toCommand() {
      var userToken = UUID.randomUUID().toString();

      return UserCommand.builder()
        .email(this.email)
        .name(this.name)
        .password(this.password)
        .userToken(userToken)
        .build();
    }

  }

  @Getter
  @JsonInclude(Include.NON_NULL)
  public static class UserResponse {
    private final String email;
    private final String name;
    private final String userToken;
    private final List<OrderResponse> orders = new ArrayList<>();

    public UserResponse(UserInfo userInfo) {
      this.email = userInfo.getEmail();
      this.name = userInfo.getName();
      this.userToken = userInfo.getUserToken();
    }
  }

  @Data
  public static class loginRequest {

    @Email
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String password;

  }

}
