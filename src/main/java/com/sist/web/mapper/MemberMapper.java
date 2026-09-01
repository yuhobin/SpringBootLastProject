package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.MemberVO;

@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT username, password, enabled, sex, name, member_id "
			+"FROM member "
			+"WHERE username=#{username}")
	public MemberVO findByUsername(String username);
}
