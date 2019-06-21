package top.sinfulxx.loki.service;

import com.alibaba.fastjson.JSONObject;
import top.sinfulxx.loki.pojo.GithubUsersVO;

/**
 * @author hanyuzhe
 * @date 2019/6/21 下午3:41
 * @since 1.0
 */
public interface UserService {
    void userLogin(GithubUsersVO githubUsersVO);
}
