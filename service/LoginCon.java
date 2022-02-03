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
		// 이메일, 패스워드를 가져오시오
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO member = dao.memberLogin(email, pw);
		
		if (member != null) {
			// 내장객체가 아니므로 세션 선언(쿠키세션 파일 참조)
			System.out.println("로그인 성공!");
			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			response.sendRedirect("main_jstl.jsp");
		} else {
			// 로그인 실패 알림창 띄운 후 main.jsp로 이동하기
			// * JoinCon else문 참고
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 실패..!');");
			out.print("location.href = 'main_jstl.jsp';");
			out.print("</script>");
			
			
			
		}
	}

}
