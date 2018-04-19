package top.liubowen.learning04.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.liubowen.learning04.entity.UserInfo;

import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/19 17:26
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void fetchUsers() {
        List<UserInfo> users = userRepository.findAll();
        users.forEach(user -> log.info("user : " + user));
    }

    @Test
    public void findUser() {
        UserInfo user = userRepository.findById(10003L).get();
        log.info("user : " + user);
    }

    @Test
    public void save() {
        UserInfo user = new UserInfo("张三", 135);
        userRepository.saveAndFlush(user);
        log.info("user : " + user);
    }

    @Test
    public void update() {
        UserInfo user = userRepository.findById(10004L).get();
        user.setName(user.getName() + "udpate");
        user.setAge(user.getAge() + 1);
        userRepository.saveAndFlush(user);
        log.info("user : " + user);
    }

    @Test
    public void delete() {
        log.info(">>>>>>>>>>>befor>>>>>>>>>>>>>>>>");
        List<UserInfo> befor = userRepository.findAll();
        befor.forEach(user -> log.info("user : " + user));

        userRepository.deleteById(10004L);

        log.info(">>>>>>>>>>>after>>>>>>>>>>>>>>>>");
        List<UserInfo> users = userRepository.findAll();
        users.forEach(user -> log.info("user : " + user));
    }

}