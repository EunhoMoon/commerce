package me.janek.user.domain.user;

public interface UserService {

  UserInfo createUser(UserCommand command);

}
