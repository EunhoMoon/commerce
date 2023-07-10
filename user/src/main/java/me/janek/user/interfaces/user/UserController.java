package me.janek.user.interfaces.user;

import lombok.RequiredArgsConstructor;
import me.janek.user.application.UserDetailFacade;
import me.janek.user.domain.user.UserInfo;
import me.janek.user.domain.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static me.janek.user.interfaces.user.UserDto.RegisterRequest;
import static me.janek.user.interfaces.user.UserDto.UserResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserDetailFacade userDetailFacade;

    @PostMapping()
    public ResponseEntity<UserResponse> userRegistration(
        @RequestBody RegisterRequest request
    ) {
        var command = request.toCommand();
        var createdUserInfo = userService.createUser(command).toDto();

        return ResponseEntity.status(CREATED).body(createdUserInfo);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var allUsers = userService.getAllUsers().stream()
            .map(UserInfo::toDto)
            .collect(Collectors.toList());

        return ResponseEntity.status(OK).body(allUsers);
    }

    @GetMapping("/{user-token}")
    public ResponseEntity<UserResponse> getUserByToken(
        @PathVariable("user-token") String userToken
    ) {
        var findUser = userDetailFacade.getUserInfoAndUsersOrderList(userToken);

        return ResponseEntity.status(OK).body(findUser);
    }

}
