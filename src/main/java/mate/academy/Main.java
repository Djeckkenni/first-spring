package mate.academy;

import mate.academy.config.AppConfig;
import mate.academy.model.User;
import mate.academy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User bob = new User("Bob", "Singlar", "bob@gmail.com");
        userService.add(bob);
        User gabby = new User("Gabby", "Gabby", "gabby@gmail.com");
        userService.add(gabby);
        userService.listUsers().forEach(user -> System.out.println(user.toString()));
    }
}
