package com.sist.web.vo;

import lombok.Data;

/*
 *  NO            NOT NULL NUMBER         
	TITLE         NOT NULL VARCHAR2(1000) 
	INSTRUCTOR_NO NOT NULL NUMBER         
	STAR                   NUMBER(3,1)    
	STUDENT_COUNT          NUMBER         
	PAY_PRICE              NUMBER         
	REGULAR_PRICE          NUMBER         
	CONTENT                CLOB           
	IMAGES                 CLOB    
 */
@Data
public class CourseVO {
	private int no, instructor_no, student_count, pay_price, regular_price;
	private String title, content, images;
	private double star;
}
