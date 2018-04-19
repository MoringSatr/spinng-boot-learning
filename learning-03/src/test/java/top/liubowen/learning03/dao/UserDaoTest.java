package top.liubowen.learning03.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.liubowen.learning03.Application;
import top.liubowen.learning03.entity.UserInfo;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/19 13:01
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@Slf4j
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void fetchUsers() {
        List<UserInfo> users = userDao.fetchUsers();
        users.forEach(user -> log.info("user : " + user));
    }

    @Test
    public void findUser() {
        log.info("user : " + userDao.findUser(10001));
    }

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo("张三", 15);
        Assert.assertTrue(userDao.save(userInfo));
        log.info("user : " + userInfo);
    }

    @Test
    public void update() {
        UserInfo user = userDao.findUser(10001);
        user.setName(user.getName() + "udpate");
        user.setAge(user.getAge() + 1);
        Assert.assertTrue(userDao.update(user));
        log.info("user : " + user);
    }

    @Test
    public void delete() {
        log.info(">>>>>>>>>>>befor>>>>>>>>>>>>>>>>");
        List<UserInfo> befor = userDao.fetchUsers();
        befor.forEach(user -> log.info("user : " + user));

        Assert.assertTrue(userDao.delete(10005));

        log.info(">>>>>>>>>>>after>>>>>>>>>>>>>>>>");
        List<UserInfo> users = userDao.fetchUsers();
        users.forEach(user -> log.info("user : " + user));
    }
}