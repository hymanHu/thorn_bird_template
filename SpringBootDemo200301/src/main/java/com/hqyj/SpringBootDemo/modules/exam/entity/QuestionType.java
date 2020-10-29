package com.hqyj.SpringBootDemo.modules.exam.entity;

/**
 * Description: 题目类型
 * @author HymanHu
 * @date 2020-10-29 13:30:34
 */
public enum QuestionType {
	SINGLE_CHOICE("singleChoice"), 
	MULTIPLE_CHOICE("multipleChoice"),
	FILL_BLANK("fillBlank"),
	SHORT_ANSWER("shortAnswer"),
	PROGRAMMING("programming");
	
	public String name;

	private QuestionType(String name) {
		this.name = name;
	}
}
