package ott.j4jg_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class J4jgGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(J4jgGatewayApplication.class, args);
    }

}
