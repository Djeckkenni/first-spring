package mate.academy.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.UserResponseDto;
import mate.academy.model.User;
import mate.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectData() {
        User bob = new User("Bob", "Singlar", "bob@gmail.com");
        userService.add(bob);
        User gabby = new User("Gabby", "Gabby", "gabby@gmail.com");
        userService.add(gabby);
        return "Data were injected";
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return getDto(userService.get(userId));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<User> users = userService.listUsers();
        return users.stream()
                .map(this::getDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getDto(User user) {
        UserResponseDto dto = new UserResponseDto(user.getFirstName(),
                user.getLastName(), user.getMail());
        return dto;
    }
}
