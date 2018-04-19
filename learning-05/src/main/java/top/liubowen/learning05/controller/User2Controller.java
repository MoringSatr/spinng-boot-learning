package top.liubowen.learning05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.liubowen.learning05.common.ResultMessage;
import top.liubowen.learning05.entity.second.UserInfo;
import top.liubowen.learning05.service.User2Service;

/**
 * @author liubowen
 * @date 2018/4/18 13:13
 * @description
 */
@RestController
@RequestMapping("/user2")
public class User2Controller {

    @Autowired
    private User2Service user2Service;

    @GetMapping("/list")
    public ResultMessage fetchUsers() {
        return user2Service.fetchUsers();
    }

    @GetMapping("/{id}")
    public ResultMessage findUser(@PathVariable("id") long id) {
        return user2Service.findUser(id);
    }

    @PutMapping("/")
    public ResultMessage addUser(@RequestParam("name") String name, @RequestParam("age") int age) {
        UserInfo userInfo = new UserInfo(name, age);
        return user2Service.addUser(userInfo);
    }

    @PostMapping("/{id}")
    public ResultMessage updateUser(@PathVariable("id") long id, @RequestParam("name") String name, @RequestParam("age") int age) {
        return user2Service.updateUser(id, name, age);
    }

    @DeleteMapping("/{id}")
    public ResultMessage deleteUser(@PathVariable("id") long id) {
        return user2Service.deleteUser(id);
    }

}
