package top.liubowen.learning03.common;

/**
 * @author liubowen
 * @date 2018/4/18 10:39
 * @description 返回提示码
 */
public enum ResultCode {

    ERROR(0, "未知错误"), SUCCESS(1, "成功"), USER_NOT_EXIST(1001, "用户不存在"), SAVE_USER_ERROR(1002, "保存用户错误"), UPDATE_USER_ERROR(1003,
            "修改用户错误"), DELETE_USER_ERROR(1004, "删除用户错误"),;

    public final int code;
    public final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
