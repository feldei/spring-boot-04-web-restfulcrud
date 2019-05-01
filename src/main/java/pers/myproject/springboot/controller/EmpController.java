package pers.myproject.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.expression.Fields;
import pers.myproject.springboot.dao.DepartmentDao;
import pers.myproject.springboot.dao.EmployeeDao;
import pers.myproject.springboot.entities.Employee;

import java.lang.reflect.Field;
import java.util.*;

@Controller
public class EmpController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "/emps")
    public String list(Model model,Map<String,Object> map) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Collection<Employee> employees = employeeDao.getAll();

        //将数据放到请求域中,用Model或者Map封装都可以
        model.addAttribute("emps",employees);
        map.put("empList",employees);


        return "emp/list";
    }


    @GetMapping("/addPage")
    public String toAddPage(Map<String,Object> map){
        //跳转添加页面
        map.put("departments",departmentDao.getDepartments());

        //如果是真实路径不用加 / ，如果是逻辑逻辑则要加，表示当前项目
        return "emp/add";
    }

    //员工添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定：请求参数的名字必须和javaBean入参属性名一致
    @PostMapping("/addEmp")
    public String addEmp(Employee employee){
        //跳转到员工列表页面
        System.out.println("新增的员工信息："+employee.toString());
        employeeDao.save(employee);
        //redirect表示重定向到一个地址
        //forward表示转发到一个地址
       return "redirect:/emps";//表示当前项目下的
    }


    //跳转到编辑页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable//绑定路径变量
                             Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //添加和修改共同一个页面
        return "emp/add";
    }

    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee.toString());
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
