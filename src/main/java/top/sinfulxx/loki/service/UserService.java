package top.sinfulxx.loki.service;

import top.sinfulxx.loki.pojo.GithubUsersVO;

/**
 * @author hanyuzhe
 * @date 2019/6/21 下午3:41
 * @since 1.0
 */
public interface UserService {

    /**
     * 登录用户逻辑
     * @param githubUsersVO
     * @return 返回一个token
     */
    String userLogin(GithubUsersVO githubUsersVO);
}
