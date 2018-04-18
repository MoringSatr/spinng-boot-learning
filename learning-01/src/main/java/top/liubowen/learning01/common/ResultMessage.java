package top.liubowen.learning01.common;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author liubowen
 * @date 2018/4/18 10:37
 * @description
 */
public class ResultMessage {

    private int code;
    private String message;
    private Map<String, Object> data;

    public ResultMessage(ResultCode resultCode) {
        this.code = resultCode.code;
        this.message = resultCode.message;
        data = Maps.newHashMap();
    }

    public ResultMessage(ResultCode resultCode, Map<String, Object> data) {
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }

    public static ResultMessage success() {
        return new ResultMessage(ResultCode.SUCCESS);
    }

    public static ResultMessage error() {
        return new ResultMessage(ResultCode.ERROR);
    }

    public void add(String key, Object value) {
        data.put(key, value);
    }

    public void addAll(Map<String, Object> data) {
        this.data.putAll(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMessage{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data.toString() + '}';
    }
}
