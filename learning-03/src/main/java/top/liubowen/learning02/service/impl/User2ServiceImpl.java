package top.liubowen.learning02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liubowen.learning02.common.ResultCode;
import top.liubowen.learning02.common.ResultMessage;
import top.liubowen.learning02.dao.User2Dao;
import top.liubowen.learning02.entity.UserInfo;
import top.liubowen.learning02.service.User2Service;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Dao user2Dao;

    @Override
    public ResultMessage fetchUsers() {
        List<UserInfo> users = user2Dao.fetchUsers();

        ResultMessage success = ResultMessage.success();
        success.add("users", users);
        return success;
    }

    @Override
    public ResultMessage findUser(long userId) {
        UserInfo user = user2Dao.findUser(userId);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage addUser(UserInfo userInfo) {
        if (!user2Dao.save(userInfo)) {
            return new ResultMessage(ResultCode.SAVE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user2Dao.findUser(userInfo.getId()));
        return success;
    }

    @Override
    public ResultMessage updateUser(long id, String name, int age) {
        UserInfo user = user2Dao.findUser(id);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        user.setName(name);
        user.setAge(age);

        if (!user2Dao.update(user)) {
            return new ResultMessage(ResultCode.UPDATE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        if (!user2Dao.delete(userId)) {
            return new ResultMessage(ResultCode.DELETE_USER_ERROR);
        }
        return ResultMessage.success();
    }
}
