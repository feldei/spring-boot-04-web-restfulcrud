package pers.myproject.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import pers.myproject.springboot.component.LoginHandlerInterceptor;

//@EnableWebMvc//全面接管MVC
//使用WebMvcConfigurerAdapter可以来扩展SpringMvc的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/test").setViewName("success");
//    }


    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //添加视图映射
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                super.addInterceptors(registry);
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                                            .excludePathPatterns("/index.html","/","/user/login");//排除拦截这三个情况
            }
        };
    }
}
