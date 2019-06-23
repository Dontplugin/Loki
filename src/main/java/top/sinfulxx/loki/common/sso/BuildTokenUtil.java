package top.sinfulxx.loki.common.sso;



import org.springframework.util.StringUtils;
import top.sinfulxx.loki.common.HashUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sid on 2017/11/13.
 */
public class BuildTokenUtil {

    public static String generateSid(String userId) {
        if(StringUtils.isEmpty(userId)) {
            return generateSid();
        }

        StringBuilder sb = new StringBuilder();
        sb.append('1')
                .append(genRandomID())
                .append('_')
                .append(userId);
        return sb.toString();
    }

    /**
     * 生成10位UUID
     *
     * @return
     */
    private static String genRandomID() {
        UUID uuid = UUID.randomUUID();

        // 改变uuid的生成规则
        return HashUtil.convertToHashStr(uuid.getMostSignificantBits(), 2)
                + HashUtil.convertToHashStr(uuid.getLeastSignificantBits(), 2);
    }


    public static String generateSid(){
        Date now = new Date();
        int seed = 1000;
        String ts = now.getTime()+""+(int)(Math.random()*seed);
        return ts;
    }
}
