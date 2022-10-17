package spring.week5_teamcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week5TeamcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week5TeamcrudApplication.class, args);
    }

}
