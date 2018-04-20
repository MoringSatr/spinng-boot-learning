package top.liubowen.learning06.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liubowen.learning06.common.ResultCode;
import top.liubowen.learning06.common.ResultMessage;
import top.liubowen.learning06.entity.UserInfo;
import top.liubowen.learning06.mapper.UserMapper;
import top.liubowen.learning06.service.UserService;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultMessage fetchUsers() {
        List<UserInfo> users = userMapper.selectAll();

        ResultMessage success = ResultMessage.success();
        success.add("users", users);
        return success;
    }

    @Override
    public ResultMessage findUser(long userId) {
        UserInfo user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage addUser(UserInfo userInfo) {
        if (userMapper.insertUseGeneratedKeys(userInfo) != 1) {
            return new ResultMessage(ResultCode.SAVE_USER_ERROR);
        }
        ResultMessage success = ResultMessage.success();
        success.add("user", userInfo);
        return success;
    }

    @Override
    public ResultMessage updateUser(long id, String name, int age) {
        UserInfo user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        user.setName(name);
        user.setAge(age);
        if (userMapper.updateByPrimaryKeySelective(user) != 1) {
            return new ResultMessage(ResultCode.UPDATE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        if (userMapper.deleteByPrimaryKey(userId) != 1) {
            return new ResultMessage(ResultCode.DELETE_USER_ERROR);
        }
        return ResultMessage.success();
    }
}
