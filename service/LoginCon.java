package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

public class LoginCon implements iCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �̸���, �н����带 �������ÿ�
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO member = dao.memberLogin(email, pw);
		
		if (member != null) {
			// ���尴ü�� �ƴϹǷ� ���� ����(��Ű���� ���� ����)
			System.out.println("�α��� ����!");
			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			response.sendRedirect("main_jstl.jsp");
		} else {
			// �α��� ���� �˸�â ��� �� main.jsp�� �̵��ϱ�
			// * JoinCon else�� ����
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('�α��� ����..!');");
			out.print("location.href = 'main_jstl.jsp';");
			out.print("</script>");
			
			
			
		}
	}

}