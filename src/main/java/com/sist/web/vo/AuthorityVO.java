package com.sist.web.vo;

import lombok.Data;

/*
 *  NO        NOT NULL NUMBER       
	MEMBER_ID NOT NULL NUMBER       
	AUTHORITY NOT NULL VARCHAR2(20) 
 */
@Data
public class AuthorityVO {
	private int no, member_id;
	private String authority;
}
