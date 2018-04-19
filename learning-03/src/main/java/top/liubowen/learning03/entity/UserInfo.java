package top.liubowen.learning03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liubowen
 * @date 2018/4/18 12:15
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private long id;

    private String name;

    private int age;

}
