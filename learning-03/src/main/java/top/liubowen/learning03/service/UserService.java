package top.liubowen.learning03.service;

import top.liubowen.learning03.common.ResultMessage;
import top.liubowen.learning03.entity.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
public interface UserService {

    ResultMessage fetchUsers();

    ResultMessage findUser(long userId);

    ResultMessage addUser(UserInfo userInfo);

    ResultMessage updateUser(long id, String name, int age);

    ResultMessage deleteUser(long userId);

}
