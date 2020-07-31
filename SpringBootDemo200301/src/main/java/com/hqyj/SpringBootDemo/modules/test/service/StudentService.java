package com.hqyj.SpringBootDemo.modules.test.service;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description: Student Service
 * @author: HymanHu
 * @date: 2020年7月30日
 */
public interface StudentService {

	Result<Student> insertStudent(Student student);

	Student getStudentById(int studentId);

	Page<Student> getStudentsBySearchVo(SearchVo searchVo);

	List<Student> getStudentsByParams(String studentName);

	List<Student> getStudentsByParams(String studentName, int cardId);
}
