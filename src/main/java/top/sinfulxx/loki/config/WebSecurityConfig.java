package top.sinfulxx.loki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.sinfulxx.loki.common.RedisClient;
import top.sinfulxx.loki.common.sso.LoginUtils;
import top.sinfulxx.loki.common.sso.UserContextHandler;
import top.sinfulxx.loki.pojo.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hanyuzhe
 * @date 2019/6/19 下午5:57
 * @since 1.0
 */
@Slf4j
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    private static final String LOGIN_URL = "https://github.com/login/oauth/authorize?client_id=f8d52cbce204df95aae1&redirect_uri=http://localhost:8080/oauth/redirect";

    @Autowired
    private RedisClient redisClient;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/oauth/redirect");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/index");
        addInterceptor.excludePathPatterns("/login/**");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            // 查询request中是否存在token，不存在则执行登录流程
            String token = LoginUtils.getToken(request);
            Users users = redisClient.get(token, Users.class);
            if (users != null) {
                UserContextHandler.setUser(users);
                return true;
            }

            // 跳转登录
            String url = LOGIN_URL;
            response.sendRedirect(url);
            return false;
        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                               ModelAndView modelAndView) throws Exception {
            log.info("postHandle...");
        }

        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    Object o, Exception e) throws Exception {
            log.info("afterCompletion...");
        }
    }
}