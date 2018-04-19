package top.liubowen.learning02.service;

import top.liubowen.learning02.common.ResultMessage;
import top.liubowen.learning02.entity.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
public interface User2Service {

    ResultMessage fetchUsers();

    ResultMessage findUser(long userId);

    ResultMessage addUser(UserInfo userInfo);

    ResultMessage updateUser(long id, String name, int age);

    ResultMessage deleteUser(long userId);

}
