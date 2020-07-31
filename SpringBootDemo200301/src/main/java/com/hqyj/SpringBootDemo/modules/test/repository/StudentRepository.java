package com.hqyj.SpringBootDemo.modules.test.repository;

import com.hqyj.SpringBootDemo.modules.test.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Student Repository
 * @author: HymanHu
 * @date: 2020年7月30日
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop2ByStudentNameLike(String studentName);

    /**
     * sql查询，使用原生的 sql 语句，传参方式类似于 hql 语句
     * hql查询，传参方式1：?1，?加数字表示占位符，从 1 开始
     * hql查询，传参方式2 :userName，:加上变量名，这里是与方法参数中有 @PARAM 的值匹配
     * -返回值，相当于 List<Object>, 可使用String[]作返回类型，可视作 jpa 调用 List.toArray(new String[List.size()])
     *
     */
//    @Query(nativeQuery = true,
//            value = "select * from h_student where student_name = :studentName and card_id = :cardId")
//    @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")
    @Query(value = "select s from Student s where s.studentName = :studentName and s.studentCard.cardId = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName, @Param("cardId") int cardId);
}
