package top.sinfulxx.loki.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信开发者认证token
 *
 * @author hanyuzhe
 * @date 2019/6/20 下午4:11
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @RequestMapping("/token")
    public String token(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam("echostr") String echostr) {
        log.info("signature={}, timestamp={}, nonce={}, echostr={}", signature, timestamp, nonce, echostr);
        return echostr;
    }
}
