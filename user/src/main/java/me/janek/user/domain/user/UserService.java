package me.janek.user.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  UserInfo createUser(UserCommand command);

  UserInfo getUserByUserToken(String userToken);

  List<UserInfo> getAllUsers();

}
