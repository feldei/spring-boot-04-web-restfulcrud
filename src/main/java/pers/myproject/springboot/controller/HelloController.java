package pers.myproject.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.myproject.springboot.exception.UserNotExitException;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(@RequestParam("usernme") String username){
        if (username.equals("aaa")){
            throw new UserNotExitException();
        }
        return "Hellop";
    }

    @RequestMapping("/test")
    public String success(Map<Object,Object> map){
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return "login";
    }
}
