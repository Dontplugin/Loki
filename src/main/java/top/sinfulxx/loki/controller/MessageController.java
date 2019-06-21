package top.sinfulxx.loki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanyuzhe
 * @date 2019/6/19 下午4:54
 * @since 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {



    @RequestMapping("send")
    public String send(@RequestParam("msg") String msg) {
        return msg;
    }
}
