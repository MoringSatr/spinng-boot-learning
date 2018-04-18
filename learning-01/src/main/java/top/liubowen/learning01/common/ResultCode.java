package top.liubowen.learning01.common;

/**
 * @author liubowen
 * @date 2018/4/18 10:39
 * @description 返回提示码
 */
public enum ResultCode {

    ERROR(0, "未知错误"), SUCCESS(1, "成功"),;

    public final int code;
    public final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
