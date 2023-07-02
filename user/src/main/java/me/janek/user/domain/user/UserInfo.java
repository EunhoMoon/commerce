package me.janek.user.domain.user;

import lombok.Getter;

import static me.janek.user.interfaces.user.UserDto.UserResponse;

@Getter
public class UserInfo {

    private final String email;
    private final String name;
    private final String userToken;

    public UserInfo(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.userToken = user.getUserToken();
    }

    public UserResponse toDto() {
        return new UserResponse(this);
    }

}
