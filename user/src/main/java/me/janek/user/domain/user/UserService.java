package me.janek.user.domain.user;

import java.util.List;

public interface UserService {

  UserInfo createUser(UserCommand command);

  UserInfo getUserByUserToken(String userToken);

  List<UserInfo> getAllUsers();

}
