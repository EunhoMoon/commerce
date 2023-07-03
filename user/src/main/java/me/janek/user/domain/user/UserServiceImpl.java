package me.janek.user.domain.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.infrastructure.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserInfo createUser(UserCommand command) {
        var encryptedPassword = passwordEncoder.encode(command.getPassword());
        var createdUser = command.toEntity(encryptedPassword);

        userRepository.save(createdUser);

        return new UserInfo(createdUser);
    }

    @Override
    public UserInfo getUserByUserToken(String userToken) {
        var findUser = userRepository.findByUserToken(userToken)
            .orElseThrow(RuntimeException::new);
        return new UserInfo(findUser);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserInfo::new).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var findUser = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(
            findUser.getEmail(),
            findUser.getEncryptedPassword(),
            true,
            true,
            true,
            true,
            new ArrayList<>()
        );
    }
}
