package com.hqyj.SpringBootDemo.modules.test.repository;

import com.hqyj.SpringBootDemo.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description Card Repository
 * @Author HymanHu
 * @Date 2020/7/31 9:48
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}
