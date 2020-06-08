package mate.academy.service;

import java.util.List;
import mate.academy.dto.UserResponseDto;
import mate.academy.model.User;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    UserResponseDto get(Long userId);
}
