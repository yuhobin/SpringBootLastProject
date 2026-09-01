package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface AuthorityMapper {
	@Select("SELECT no, member_id, authority "
			+"FROM authority "
			+"WHERE member_id=#{member_id}")
	public List<AuthorityVO> getAuthorityData(int member_id);
}
