package com.hqyj.SpringBootDemo.modules.test.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import com.hqyj.SpringBootDemo.modules.test.repository.StudentRepository;
import com.hqyj.SpringBootDemo.modules.test.service.StudentService;

/**
 * @Description: Student Service Impl
 * @author: HymanHu
 * @date: 2020年7月30日
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Result<Student> insertStudent(Student student) {
		student.setCreateDate(LocalDateTime.now());
		studentRepository.saveAndFlush(student);
		return new Result<Student>(ResultStatus.SUCCESS.status, "Insert success.", student);
	}

}
