package com.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// 어노테이션
// getter / setter 메소드 자동생성
@Data
// 기본 생성자 생성
@NoArgsConstructor
// 모든 필드변수를 초기화하는 생성자 생성
@AllArgsConstructor

public class MemberDTO {
	private String m_email;
	private String m_pw;
	private String m_tel;
	private String m_address;
	
}
