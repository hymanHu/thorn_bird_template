package com.hqyj.SpringBootDemo.modules.test.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
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

	@Override
	public Student getStudentById(int studentId) {
		return studentRepository.findById(studentId).get();
	}

	@Override
	public Page<Student> getStudentsBySearchVo(SearchVo searchVo) {
		String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
				"student_id" : searchVo.getOrderBy();
		Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) ||
				searchVo.getSort().equalsIgnoreCase("asc") ?
				Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sort = new Sort(direction, orderBy);
		// 当前页起始为 0
		Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1, searchVo.getPageSize(), sort);
		
		// 如果 keyWord 为 null，则设置的 studentName 不参与查询条件
		Student student = new Student();
		student.setStudentName(searchVo.getKeyWord());
		ExampleMatcher matcher = ExampleMatcher.matching()
				// 全部模糊查询，即 %{studentName} %
				.withMatcher("studentName", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("studentName", match -> match.contains())
				// 忽略字段，即不管password是什么值都不加入查询条件
				.withIgnorePaths("studentId");
		Example<Student> example = Example.of(student, matcher);
		
		return studentRepository.findAll(example, pageable);
	}

	@Override
	public List<Student> getStudentsByParams(String studentName) {
//		return Optional
//				.ofNullable(studentRepository.findByStudentName(studentName))
//				.orElse(Collections.emptyList());
//		return Optional.ofNullable(
//				studentRepository.findByStudentNameLike(String.format("%s%s%s", "%", studentName, "%")))
//				.orElse(Collections.emptyList());
		return Optional.ofNullable(
				studentRepository.findTop2ByStudentNameLike(String.format("%s%s%s", "%", studentName, "%")))
				.orElse(Collections.emptyList());
	}

	@Override
	public List<Student> getStudentsByParams(String studentName, int cardId) {
		return Optional
				.ofNullable(studentRepository.getStudentsByParams(studentName, cardId))
				.orElse(Collections.emptyList());
	}
}
