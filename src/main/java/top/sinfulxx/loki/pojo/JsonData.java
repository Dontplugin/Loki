package top.sinfulxx.loki.pojo;


import java.io.Serializable;

/**
 * 后端相应包装类, 状态码200 返回成功, 500 响应有问题
 *
 * @author hanyuzhe
 * @date 2019/6/22 下午1:51
 * @since 1.0
 */
public class JsonData implements Serializable {

    //状态码
    private Integer code;
    //返回数据
    private Object data;
    //返回信息描述
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonData(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public JsonData() {
    }
    // 成功，只返回成功状态码
    public static JsonData buildSucceful() {
        return new JsonData(200, null, null);
    }
    // 成功，传入状态码和数据
    public static JsonData ok(Object data) {
        return new JsonData(200, data, null);
    }
    // 失败，传入描述信息,状态码
    public static  JsonData buildError(String message){
        return new JsonData(500,null,message);
    }
    // 失败，传入描述信息,状态码
    public static  JsonData buildError(Integer code,String message){
        return new JsonData(code,null,message);
    }
    // 成功，传入数据,及描述信息
    public static  JsonData buildSuccesful(Object data,String message){
        return new JsonData(200,data,message);
    }
    // 成功，传入数据,及状态码
    public static  JsonData buildSuccesful(Object data,Integer code){
        return new JsonData(code,data,null);
    }
}