package wilfer.webfluxpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class WebfluxPostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxPostgresApplication.class, args);
    }

}
