package top.sinfulxx.loki.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanyuzhe
 * @date 2019/6/21 上午10:47
 * @since 1.0
 */
@Configuration
public class BeanConfig {

    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;

    @Bean
    public String clientId() {
        return clientId;
    }
    @Bean
    public String clientSecret() {
        return clientSecret;
    }

}
