package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.Entity.Student;
import com.example.student.repository.StudentRepository;

@Controller
public class StudentController 
{
	@Autowired
	StudentRepository repository;
	
	@GetMapping("/")
	public String Main()
	{
		return "main.html";
	}
	
	@GetMapping("/add-stu")
	public String Add()
	{
		return "add.html";
	}
	
	@PostMapping("/add-stu")
	public String addStu(@ModelAttribute Student stu ,ModelMap map)
	{
		repository.save(stu);
		map.put("message", "Student Added Successfully");
		return "main.html";
	}
	
	@GetMapping("/view-stu")
	public String viewStudent(ModelMap map)
	{
		List<Student> stu = repository.findAll();
		map.put("stu", stu);
		return "student";
	}
	
	
	@GetMapping("/delete")
	public String DeleteStu(@RequestParam Long id, ModelMap map)
	{
		repository.deleteById(id);
		map.put("message", "Student data got successfully deleted ");
		return viewStudent(map);
	}
	
	@GetMapping("/edit")
	public String editStu(@RequestParam Long id, ModelMap map)
	{
		Student stu = repository.findById(id).orElseThrow();
		map.put("stu", stu);
		return "edit.html";
	}
	
	@PostMapping("/update")
	public String UpdateStu(@ModelAttribute Student stu, ModelMap map)
	{
		repository.save(stu);
		map.put("stu", "Student Data got Updated Successfully");
		return viewStudent(map);
		
	}
	
}
