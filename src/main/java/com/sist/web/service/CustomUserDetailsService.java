package com.sist.web.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.web.vo.AuthorityVO;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	private final MemberService mService;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO member=mService.findByUsername(username);
		// ID가 오라클에 존재하지 않는 경우 없는 경우
		if(member==null) {
			// 예외처리 임의 발생
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다 :"+username);
		}
		// 휴먼 계정인지
		if(member.getEnabled()!=1) {
			throw new UsernameNotFoundException("비활성화된 계정입니다");
		}
		List<AuthorityVO> authList=
				mService.getAuthorityData(member.getMember_id());
		// 권한을 => SpringSecurity로 변환
		List<SimpleGrantedAuthority> authorities=authList.stream()
														.map(a -> new SimpleGrantedAuthority(a.getAuthority()))
														.toList();
		// UserDetails에 저장
		return User.builder()
				.username(member.getUsername())
				.password(member.getPassword())
				.authorities(authorities)
				.build();
	}
	
}
