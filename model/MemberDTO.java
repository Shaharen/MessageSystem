package com.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// ������̼�
// getter / setter �޼ҵ� �ڵ�����
@Data
// �⺻ ������ ����
@NoArgsConstructor
// ��� �ʵ庯���� �ʱ�ȭ�ϴ� ������ ����
@AllArgsConstructor

public class MemberDTO {
	private String m_email;
	private String m_pw;
	private String m_tel;
	private String m_address;
	
}
