package be.kdg.prog6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern="be.kdg.prog6.*.*Application"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern="be.kdg.prog6.*.*ModuleConfig")
})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
//
//@RestController
//class HelloWorldController {
//    @GetMapping("/")
//    public String hello() {
//        return "hello world!";
//    }
//}
