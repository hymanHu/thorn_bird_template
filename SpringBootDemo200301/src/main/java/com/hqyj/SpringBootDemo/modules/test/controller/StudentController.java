package com.hqyj.SpringBootDemo.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import com.hqyj.SpringBootDemo.modules.test.service.StudentService;

/**
 * @Description: Student Controller
 * @author: HymanHu
 * @date: 2020年7月30日
 */
@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	/**
	 * 127.0.0.1/api/student ---- post
	 */
	@PostMapping(value = "/student", consumes = "application/json")
	public Result<Student> insertStudent(@RequestBody Student student) {
		return studentService.insertStudent(student);
	}
}
