package com.hqyj.SpringBootDemo.modules.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqyj.SpringBootDemo.modules.test.entity.Student;

/**
 * @Description: Student Repository
 * @author: HymanHu
 * @date: 2020年7月30日
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
