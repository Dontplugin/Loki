package top.sinfulxx.loki.service;

import java.util.Map;

/**
 * @author hanyuzhe
 * @date 2019/6/21 下午4:38
 * @since 1.0
 */
public interface SsoService {
    /**
     * 设置一个登录信息
     * @param sid
     * @param context
     */
    void set(String sid, Map<String, String> context);

    /**
     * 获取一个登录信息, 不包含菜单信息, 同时会touch
     * @param sid
     * @return
     */
    Map<String, String> get(String sid);

    /**
     * 获取一个完整的登录信息, 包含菜单信息, 同时会touch
     * @param sid
     * @return
     */
    Map<String, String> getFull(String sid);

    /**
     * 刷新一个缓存的有效时间
     * @param sid
     */
    void touch(String sid, int expireSecond);

    /**
     * 删除一个登录信息
     * @param sid
     */
    void remove(String sid);

    /**
     * 根据UserId刷新一个
     * @param userId
     * @param expireSecond
     */
    void touchByUserId(String userId, int expireSecond);

    /**
     * 删除用户的所有token
     * @param userId
     */
    void removeUserTokens(String userId);

    /**
     * 获取一个登录信息, 不会touch
     * @param sid
     * @return
     */
    Map<String, String> getNoTouch(String sid);
}
