package mate.academy.controllers;

import mate.academy.dto.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello123";
    }

    @GetMapping("/userDto")
    public UserResponseDto getUser() {
        UserResponseDto dto = new UserResponseDto("Bob","Singlar","bob@i.ua");
        return dto;

    }
}
