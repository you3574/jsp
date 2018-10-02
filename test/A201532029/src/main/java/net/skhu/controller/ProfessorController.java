package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Department;
//import net.skhu.dto.Professor;
import net.skhu.mapper.DepartmentMapper;
//import net.skhu.mapper.ProfessorMapper;

@Controller
@RequestMapping("/home") //짝짓기 후에,url에 쓰임.
public class ProfessorController {

  //  @Autowired ProfessorMapper ProfessorMapper; //mapper 만들어서 자동으로 멤버변수에 대입.
    @Autowired DepartmentMapper departmentMapper;

    @RequestMapping("test1")
    public String test1(Model model) {
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("departments", departments);
        return "home/test1";
    }
    
    @RequestMapping("test2")
    public String test2(Model model) {
        model.addAttribute("number", 123);
        return "home/test2";
    }
    

}
