package top.sinfulxx.loki.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转化成字符串
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objToString(T object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String) object : mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * 字符串转化成对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T str2Object(String str, Class<T> clazz) {
        if (str == null || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : mapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}