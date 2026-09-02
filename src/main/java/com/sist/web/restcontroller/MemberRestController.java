package com.sist.web.restcontroller;





import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.security.JWTAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final AuthenticationManager manager;
	private final JWTAuthenticationProvider provider;
	
	@RequestMapping("/member/login_ok")
	public ResponseEntity<?> login(
		@RequestParam(value="username", required = false) String username,
		@RequestParam(value = "password", required = false) String paaword
	) {
		try {
			// ID / PW 인증
			Authentication auth=manager.authenticate(
				new UsernamePasswordAuthenticationToken(username, paaword)
			);
			System.out.println("ID/PW 인증");
			// 인증된 사용자 정보 
			UserDetails user=(UserDetails)auth.getPrincipal();
			// securityContext => 사용자 정보 => getPrincipal()
			System.out.println("인증된 사용자 정보");
			// 사용자 권한 
			String role=user.getAuthorities()
							.iterator()
							.next()
							.getAuthority();
			System.out.println("사용자 권한:"+role);
			// JWT 생성
			String token=provider.createToken(user.getUsername(), role);
			System.out.println("토큰:"+token);
			// JWT Cookie 생성
			ResponseCookie cookie=ResponseCookie.from("accessToken",token)
												.httpOnly(true)
												.secure(false)
												.path("/")
												.maxAge(3600)
												.build();
			System.out.println("JWT Cookie:"+cookie);
			// 성공 여부 확인 
			return ResponseEntity.status(HttpStatus.FOUND)
								.header(
								HttpHeaders.SET_COOKIE,
								cookie.toString()
								)
								.header(
								HttpHeaders.LOCATION,
								"/"
								)
								.build();
			// => 로그인 실패 처리
		} catch (BadCredentialsException e) {
			// 로그인 실패 => ID/PW
			return ResponseEntity.status(HttpStatus.FOUND)
					.header(
					HttpHeaders.LOCATION,
					"/member/login?error=true"
					)
					.build();
		} catch (AuthenticationException e) {
			// 인증 실패
			return ResponseEntity.status(HttpStatus.FOUND)
					.header(
					HttpHeaders.LOCATION,
					"/member/login?error=true"
					)
					.build();
		} catch (Exception ex) {
			// 서버 오류
			return ResponseEntity.status(HttpStatus.FOUND)
					.header(
					HttpHeaders.LOCATION,
					"/member/login?error=true"
					)
					.build();
		}
		
	}
	@GetMapping("/member/logout")
	public ResponseEntity<Void> logout(){
		// cookie 삭제
		ResponseCookie cookie=
				ResponseCookie
				.from("accessToken","")
				.httpOnly(true)
				.secure(false)
				.path("/")
				.maxAge(0)
				.build();
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.header(
					HttpHeaders.SET_COOKIE,cookie.toString()
				)
				.header(
						HttpHeaders.LOCATION,
						"/"
				)
				.build();
	}
}
