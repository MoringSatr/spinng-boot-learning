package top.liubowen.learning02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.liubowen.learning02.common.ResultMessage;
import top.liubowen.learning02.entity.UserInfo;
import top.liubowen.learning02.service.UserService;

/**
 * @author liubowen
 * @date 2018/4/18 13:13
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResultMessage fetchUsers() {
        return userService.fetchUsers();
    }

    @GetMapping("/{id}")
    public ResultMessage findUser(@PathVariable("id") long id) {
        return userService.findUser(id);
    }

    @PutMapping("/")
    public ResultMessage addUser(@RequestParam("name") String name, @RequestParam("age") int age) {
        UserInfo userInfo = new UserInfo(name, age);
        return userService.addUser(userInfo);
    }

    @PostMapping("/{id}")
    public ResultMessage updateUser(@PathVariable("id") long id, @RequestParam("name") String name, @RequestParam("age") int age) {
        return userService.updateUser(id, name, age);
    }

    @DeleteMapping("/{id}")
    public ResultMessage deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

}
