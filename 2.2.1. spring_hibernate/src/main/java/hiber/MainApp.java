package com.example.springhibernate;

import com.example.springhibernate.config.AppConfig;
import com.example.springhibernate.model.Car;
import com.example.springhibernate.model.User;
import com.example.springhibernate.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.save(new User("Alice", 25, new Car("BMW", 3)));
        userService.save(new User("Bob", 30, new Car("Audi", 5)));
        userService.save(new User("Charlie", 35, new Car("Mercedes", 7)));
        userService.save(new User("Diana", 40, new Car("Toyota", 1)));

        System.out.println("All users:");
        userService.getAllUsers().forEach(
                u -> System.out.println(u.getName() + " — " + u.getCar().getModel()));

        User found = userService.getUserByCar("BMW", 3);
        System.out.println("Found user: " + found.getName());

        context.close();
    }
}
