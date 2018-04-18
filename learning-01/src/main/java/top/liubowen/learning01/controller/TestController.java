package top.liubowen.learning01.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liubowen.learning01.common.ResultMessage;

/**
 * @author liubowen
 * @date 2018/4/18 10:35
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hollo")
    public ResultMessage hollo() {
        ResultMessage resultMessage = ResultMessage.success();
        resultMessage.add("name", "liubowen");
        resultMessage.add("say", "hollo world!");
        return resultMessage;
    }

    @PostMapping("/success")
    public ResultMessage success() {
        return ResultMessage.success();
    }

    @PostMapping("/error")
    public ResultMessage error() {
        return ResultMessage.error();
    }
}
