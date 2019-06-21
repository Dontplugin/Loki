package top.sinfulxx.loki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.sinfulxx.loki.mapper")
public class LokiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LokiApplication.class, args);
    }

}
