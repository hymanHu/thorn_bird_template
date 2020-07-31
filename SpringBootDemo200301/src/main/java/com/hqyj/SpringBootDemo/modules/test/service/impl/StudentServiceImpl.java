package com.hqyj.SpringBootDemo.modules.test.service.impl;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import com.hqyj.SpringBootDemo.modules.test.repository.StudentRepository;
import com.hqyj.SpringBootDemo.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

//		Specification<Student> specification = new Specification() {
//			@Override
//			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
//				List<Predicate> predicates = new ArrayList<>();
//				if (StringUtils.isNotBlank(searchVo.getKeyWord())) {
//					predicates.add(criteriaBuilder.like(root.get("studentName"), query.getStudentName()))
//				}
//				return null;
//			}
//		};

		Specification<Student> specification = new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root,
										 CriteriaQuery<?> query,
										 CriteriaBuilder criteriaBuilder) {
//				List<Predicate> predicates = new ArrayList<>();
//				if (StringUtils.isNotBlank(searchVo.getKeyWord())) {
//					predicates.add(criteriaBuilder.like(root.get("studentName"), query.getStudentName()))
//				}
				return null;
			}
		};

		return studentRepository.findAll(specification, pageable);
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
