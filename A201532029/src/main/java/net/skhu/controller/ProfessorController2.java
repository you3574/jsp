package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Department;
import net.skhu.dto.Professor;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.ProfessorMapper;

@Controller
@RequestMapping("/c201532029") //짝짓기 후에,url에 쓰임.
public class ProfessorController2 {

    @Autowired ProfessorMapper ProfessorMapper; //mapper 만들어서 자동으로 멤버변수에 대입.
    @Autowired DepartmentMapper departmentMapper;
    
    @RequestMapping(value="list2", method=RequestMethod.GET)
    public String list2(Model model, @RequestParam("departmentId") String departmentId) {    	
    	int id = Integer.parseInt(departmentId);
    	List<Professor> professors = ProfessorMapper.findBydepartmentId(id);
    	model.addAttribute("professors", professors);
    	model.addAttribute("id", departmentId);
        return "c201532029/list2";
    }
        
    @RequestMapping("list1") // => /Professor/list
    public String list(Model model) { //view한테 주삼
        List<Professor> professors = ProfessorMapper.findAll();
        model.addAttribute("Professors", professors);
        return "c201532029/list1"; //veiw이름.
    }

   
    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        Professor professor = ProfessorMapper.findByProfessorId(id);
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("Professor", professor);
        model.addAttribute("departments", departments);
        return "c201532029/edit";
    }



}
