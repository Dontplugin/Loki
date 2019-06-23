package top.sinfulxx.loki.common.sso;

import top.sinfulxx.loki.common.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hanyuzhe
 * @date 2019/6/22 下午2:50
 * @since 1.0
 */
public class LoginUtils {

    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constant.SECURITY_TOKEN)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
