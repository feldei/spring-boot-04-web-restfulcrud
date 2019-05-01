package pers.myproject.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.myproject.springboot.exception.UserNotExitException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    /*
    如果发生异常，就会调用此方法，将异常对象捕获
     */
//    @ExceptionHandler(UserNotExitException.class)
//    public @ResponseBody Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    //将定制的错误信息转发到错误页面
    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request){

        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        /*(*
         * Integer statusCode = (Integer) request
         .getAttribute"javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());

        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }

}
