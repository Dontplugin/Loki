package top.sinfulxx.loki.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.sinfulxx.loki.common.UUIDUtil;
import top.sinfulxx.loki.common.sso.BuildTokenUtil;
import top.sinfulxx.loki.common.sso.UserContextHandler;
import top.sinfulxx.loki.mapper.UsersMapper;
import top.sinfulxx.loki.pojo.GithubUsersVO;
import top.sinfulxx.loki.pojo.Users;
import top.sinfulxx.loki.pojo.UsersExample;
import top.sinfulxx.loki.service.UserService;

import java.util.List;

/**
 * @author hanyuzhe
 * @date 2019/6/21 下午3:42
 * @since 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;


    @Override
    public String userLogin(GithubUsersVO githubUsersVO) {
        Users users = getUserFromJson(githubUsersVO);
        UsersExample example = new UsersExample();
        if (users == null)
            return null;

        example.createCriteria().andGithubIdEqualTo(users.getGithubId());
        List<Users> usersList = usersMapper.selectByExample(example);

        // 若果不存在用户则记录
        if (CollectionUtils.isEmpty(usersList)) {
            users.setId(UUIDUtil.getID());
            log.info("新注册了一个用户， user:[id={}, userName={}, githubId={}]", users.getId(), users.getUserName(), users.getGithubId());
            usersMapper.insert(users);
        } else {
            log.info("登录了一个用户， user:[id={}, userName={}, githubId={}]", users.getId(), users.getUserName(), users.getGithubId());
        }


        // 注册一个登录信息，返回当前服务器的token信息
        UserContextHandler.setUser(users);
        return BuildTokenUtil.generateSid(users.getId());
    }


    private Users getUserFromJson(GithubUsersVO githubUsersVO) {
        Users user = new Users();
        BeanUtils.copyProperties(githubUsersVO, user);
        user.setGithubId(githubUsersVO.getId());
        user.setUserName(githubUsersVO.getLogin());
        return user;
    }
}
