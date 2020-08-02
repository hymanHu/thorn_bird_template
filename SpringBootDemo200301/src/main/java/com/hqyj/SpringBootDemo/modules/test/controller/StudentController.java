package com.hqyj.SpringBootDemo.modules.test.controller;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import com.hqyj.SpringBootDemo.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * {"studentName":"hujiang","studentCard":{"cardId":"1"}}
	 */
	@PostMapping(value = "/student", consumes = "application/json")
	public Result<Student> insertStudent(@RequestBody Student student) {
		return studentService.insertStudent(student);
	}

	/**
	 * 127.0.0.1/api/student/1 ---- get
	 */
	@GetMapping("/student/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		return studentService.getStudentById(studentId);
	}

	/**
	 * 127.0.0.1/api/students ---- post
	 * -注意，orderBy 属性包装的是类属性，而非表字段
	 * {"currentPage":"1","pageSize":"5","orderBy":"studentId","sort":"desc","keyWord":"hu"}
	 */
	@PostMapping(value = "/students", consumes = "application/json")
	public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
		return studentService.getStudentsBySearchVo(searchVo);
	}

	/**
	 * 127.0.0.1/api/students?studentName=hujiang ---- get
	 * 127.0.0.1/api/students?studentName=hujiang&cardId=1 ---- get
	 */
	@GetMapping("/students")
	public List<Student> getStudentsByParams(
			@RequestParam String studentName, @RequestParam(required = false) Integer cardId) {
		if (cardId != null) {
			return studentService.getStudentsByParams(studentName, cardId);
		} else {
			return studentService.getStudentsByParams(studentName);
		}
	}

}
