package ott.j4jg_be;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class J4jgBeApplication {

    public static void main(String[] args) {
        // root 디렉터리에서 .env 파일을 찾도록 설정
        Dotenv dotenv = Dotenv.configure()
                .directory("../")  // root로 설정
                .load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(J4jgBeApplication.class, args);
    }
}
