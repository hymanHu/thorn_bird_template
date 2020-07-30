package com.hqyj.SpringBootDemo.modules.test.entity;

import javax.persistence.*;

/**
 * @Description StudentCard
 * @Author HymanHu
 * @Date 2020/7/30 14:25
 */
@Entity
@Table(name = "h_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;
    @Column(name = "card_no", length = 33, unique = true)
    private String cardNo;

    /**
     * OneToOne：一对一关系中，一方使用 JoinColumn 注解（有外键），另一方使用 mappedBy 属性（可选）
     * cascade：联级操作
     * fetch：加载数据策略
     */
    @OneToOne(mappedBy = "studentCard", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Student student;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
