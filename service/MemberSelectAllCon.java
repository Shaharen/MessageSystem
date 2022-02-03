package com.message.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;
import com.message.model.MessageDAO;

@WebServlet("/MemberSelectAllCon")
public class MemberSelectAllCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberDTO> members = new ArrayList<MemberDTO>();
		members = dao.memberSelectAll();
		
		request.setAttribute("members", members);
		
		// members라는 객체 정보를 select.jsp로 전달하기 위해 forward 방식 활용
		// why? redirect는 텍스트 데이터만 전달 가능함
		
		// session을 사용하지 않고 request를 사용하는 이유는?
		// -> 회원들의 정보는 select.jsp에서만 출력할 것이기 때문에 request영역에 저장
		RequestDispatcher dispatcher = request.getRequestDispatcher("select.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
