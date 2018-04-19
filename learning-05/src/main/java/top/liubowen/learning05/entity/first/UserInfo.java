package top.liubowen.learning05.entity.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author liubowen
 * @date 2018/4/18 12:15
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserInfo {

    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

}
