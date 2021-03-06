package top.liubowen.learning02.dao;

import top.liubowen.learning02.entity.UserInfo;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/18 12:15
 * @description
 */
public interface UserDao {

    List<UserInfo> fetchUsers();

    UserInfo findUser(long userId);

    boolean save(UserInfo userInfo);

    boolean update(UserInfo userInfo);

    boolean delete(long userId);
}
