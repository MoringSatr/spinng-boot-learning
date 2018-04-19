package top.liubowen.learning02.common;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Data;

/**
 * @author liubowen
 * @date 2018/4/18 10:37
 * @description
 */
@Data
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

}
