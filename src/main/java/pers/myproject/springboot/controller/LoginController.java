package pers.myproject.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username)
                &&"123456".equals(password)){
            //登录成功,防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            map.put("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登录失败
            map.put("msg","用户名或密码错误");
            return "/login";
        }

    }

//    @RequestMapping(value = "/user/signout")
//    public String signOut(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        Object username = session.getAttribute("username");
//        if(username!=null){
//            session.setAttribute("loginUser",null);
//            return "/index.html";
//        }
//        return null;
//    }
}
