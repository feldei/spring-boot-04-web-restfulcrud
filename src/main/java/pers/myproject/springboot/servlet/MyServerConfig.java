package pers.myproject.springboot.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.myproject.springboot.filter.MyFilter;

import java.util.Arrays;

@Configuration
public class MyServerConfig {

    /**
     *  注册三大组件Servlet、Filter、Listener
     */

    @Bean//将Servlet组件加载到IOC容器中
    public ServletRegistrationBean myServlet(){
        //发myServlet请求就会来到这个Servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        return registrationBean;
    }

    @Bean//Fileter组件加载到IOC容器中
    public FilterRegistrationBean myFilter(){
        //发myServlet请求就会来到这个Servlet
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());//设置定制的Filter
        registrationBean.setUrlPatterns(Arrays.asList("/hello","myServlert"));//设置要拦截的请求url

        return registrationBean;
    }


    //配置嵌入式的Servlet容器（Tomcat）
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //container就是嵌入式servlet的容器,可以定制一些tomcat的配置
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8089);//设置tomcat容器的端口
//            }
//        };
//    }

}
