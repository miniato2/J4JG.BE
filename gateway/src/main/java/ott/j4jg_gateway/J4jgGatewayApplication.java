package ott.j4jg_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 간단한 서비스간 통신, 선언적 방식으로 쉽게 RESTful API를 호출
public class J4jgGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(J4jgGatewayApplication.class, args);
    }

}