package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

@WebServlet("/UpdateCon")
public class UpdateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 이메일 정보를 가져오기 위한 세션
		// -> 회원정보 수정에서 입력받는 값이 아니므로 세션에서 가져옴
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		String email = member.getM_email();
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(new MemberDTO(email, pw, tel, address));
		
		if (cnt>0) {
			// memberUpdate로 수정된 DTO로 다시 저장
			session.setAttribute("member", new MemberDTO(email, pw, tel, address));
			response.sendRedirect("main_jstl.jsp");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원정보 수정 실패..!');");
			out.print("location.href = 'main_jstl.jsp';");
			out.print("</script>");
		}
		
	}

}
