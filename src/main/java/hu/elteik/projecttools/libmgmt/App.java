package hu.elteik.projecttools.libmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Bázis on 2017. 04. 14..
 */

/**
 * Initializes and starts Spring Boot application.
 *
 * @see hu.elteik.projecttools.libmgmt.service.MainService
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
}
