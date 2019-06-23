package top.sinfulxx.loki.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.sinfulxx.loki.common.RedisClient;
import top.sinfulxx.loki.pojo.Users;

/**
 * @author hanyuzhe
 * @date 2019/6/22 下午1:55
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisClient redisClient;



    @RequestMapping("/redis")
    public String redis(@RequestParam("param") String param) {
        Users users = new Users();
        users.setUserName("ok");
        users.setId("hweq1");

        redisClient.set("test", users);
        Users test = redisClient.get("test", Users.class);
        System.out.println(JSONObject.toJSONString(test));
        return "ok";
    }
}
