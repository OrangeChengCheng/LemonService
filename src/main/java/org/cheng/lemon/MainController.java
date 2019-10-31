package org.cheng.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainController {

    public static void main(String[] args) {

        SpringApplication.run(MainController.class, args);
        System.out.println("Lemon Service");
    }

}
