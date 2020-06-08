package mate.academy.dao;

import java.util.List;
import mate.academy.dto.UserResponseDto;
import mate.academy.model.User;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    UserResponseDto get(Long userId);
}
