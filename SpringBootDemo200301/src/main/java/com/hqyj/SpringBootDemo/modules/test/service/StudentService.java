package com.hqyj.SpringBootDemo.modules.test.service;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;

/**
 * @Description: Student Service
 * @author: HymanHu
 * @date: 2020年7月30日
 */
public interface StudentService {

	Result<Student> insertStudent(Student student);
}
