package top.sinfulxx.loki.common.sso;

import org.apache.catalina.User;
import top.sinfulxx.loki.common.Constant;
import top.sinfulxx.loki.pojo.Users;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanyuzhe
 * @date 2019/6/22 下午3:39
 * @since 1.0
 */
public class UserContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void setUser(Users users) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(Constant.CONTEXT_KEY_USER, users);
    }

    public static Users getUser(){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return (Users) map.get(Constant.CONTEXT_KEY_USER);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
