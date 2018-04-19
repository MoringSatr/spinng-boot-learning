package top.liubowen.learning03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liubowen.learning03.common.ResultCode;
import top.liubowen.learning03.common.ResultMessage;
import top.liubowen.learning03.dao.UserDao;
import top.liubowen.learning03.entity.UserInfo;
import top.liubowen.learning03.service.UserService;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultMessage fetchUsers() {
        List<UserInfo> users = userDao.fetchUsers();

        ResultMessage success = ResultMessage.success();
        success.add("users", users);
        return success;
    }

    @Override
    public ResultMessage findUser(long userId) {
        UserInfo user = userDao.findUser(userId);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage addUser(UserInfo userInfo) {
        if (!userDao.save(userInfo)) {
            return new ResultMessage(ResultCode.SAVE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", userDao.findUser(userInfo.getId()));
        return success;
    }

    @Override
    public ResultMessage updateUser(long id, String name, int age) {
        UserInfo user = userDao.findUser(id);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        user.setName(name);
        user.setAge(age);

        if (!userDao.update(user)) {
            return new ResultMessage(ResultCode.UPDATE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        if (!userDao.delete(userId)) {
            return new ResultMessage(ResultCode.DELETE_USER_ERROR);
        }
        return ResultMessage.success();
    }
}
