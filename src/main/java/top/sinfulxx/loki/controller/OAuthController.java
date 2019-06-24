package top.sinfulxx.loki.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import top.sinfulxx.loki.common.Constant;
import top.sinfulxx.loki.common.OhMyHttpUtils;
import top.sinfulxx.loki.pojo.GithubUsersVO;
import top.sinfulxx.loki.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author hanyuzhe
 * @date 2019/6/19 下午5:13
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private OhMyHttpUtils ohMyHttpUtils;
    @Autowired
    private String clientId;
    @Autowired
    private String clientSecret;
    @Autowired
    private UserService userService;

    /**
     * https://github.com/login/oauth/authorize?client_id=f8d52cbce204df95aae1&redirect_uri=http://localhost:8080/oauth/redirect
     * 这个 URL 指向 GitHub 的 OAuth 授权网址，带有两个参数：client_id告诉 GitHub 谁在请求，redirect_uri是稍后跳转回来的网址。
     *
     * @param code
     * @return
     */
    @RequestMapping("/redirect")
    public String redirect(Model model, @RequestParam("code") String code, HttpServletResponse response) {
        Map<String, String> param = new ImmutableMap.Builder<String, String>()
                .put("client_id", clientId)
                .put("client_secret", clientSecret)
                .put("code", code)
                .build();
        String accessTokenInfo = ohMyHttpUtils.postForString("https://github.com/login/oauth/access_token", param);

        // access_token=4677b2954371b71c4f2795831244b183054c111e&scope=&token_type=bearer
        String[] split = accessTokenInfo.split("&");
        String accessToken = split[0].split("=")[1];

        // 有了令牌以后，就可以向 API 请求用户数据了
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "token " + accessToken);
        GithubUsersVO githubUsersVO = null;
        try {
            githubUsersVO = ohMyHttpUtils.get("https://api.github.com/user", null, headers, GithubUsersVO.class);
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
        }
        String token = userService.userLogin(githubUsersVO);
        Cookie c1 = new Cookie(Constant.SECURITY_TOKEN, token);
        c1.setPath("/");
        response.addCookie(c1);
        model.addAttribute("msg", "ok");
        return "index";
    }
}
