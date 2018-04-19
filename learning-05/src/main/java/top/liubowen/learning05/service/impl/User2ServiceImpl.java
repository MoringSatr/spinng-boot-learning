package top.liubowen.learning05.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.liubowen.learning05.common.ResultCode;
import top.liubowen.learning05.common.ResultMessage;
import top.liubowen.learning05.entity.second.UserInfo;
import top.liubowen.learning05.repository.second.SecondUserRepository;
import top.liubowen.learning05.service.User2Service;

/**
 * @author liubowen
 * @date 2018/4/18 12:16
 * @description
 */
@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private SecondUserRepository userRepository;

    @Override
    public ResultMessage fetchUsers() {
        List<UserInfo> users = userRepository.findAll();

        ResultMessage success = ResultMessage.success();
        success.add("users", users);
        return success;
    }

    @Override
    public ResultMessage findUser(long userId) {
        UserInfo user = userRepository.findById(userId).get();
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", user);
        return success;
    }

    @Override
    public ResultMessage addUser(UserInfo userInfo) {
        UserInfo save = userRepository.save(userInfo);
        if (save == null) {
            return new ResultMessage(ResultCode.SAVE_USER_ERROR);
        }
        ResultMessage success = ResultMessage.success();
        success.add("user", save);
        return success;
    }

    @Override
    public ResultMessage updateUser(long id, String name, int age) {
        UserInfo user = userRepository.findById(id).get();
        if (user == null) {
            return new ResultMessage(ResultCode.USER_NOT_EXIST);
        }

        user.setName(name);
        user.setAge(age);
        UserInfo flush = userRepository.saveAndFlush(user);
        if (flush == null) {
            return new ResultMessage(ResultCode.UPDATE_USER_ERROR);
        }

        ResultMessage success = ResultMessage.success();
        success.add("user", flush);
        return success;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            return new ResultMessage(ResultCode.DELETE_USER_ERROR);
        }
        return ResultMessage.success();
    }
}
