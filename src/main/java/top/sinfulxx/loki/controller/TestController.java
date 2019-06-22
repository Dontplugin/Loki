package top.sinfulxx.loki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.sinfulxx.loki.common.RedisClient;

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
        String s = redisClient.get("ok");
        if (StringUtils.isEmpty(s)) {
            redisClient.set("ok", param);
        }

        return "ok";
    }
}
