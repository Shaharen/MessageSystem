package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MemberDAO;
import com.message.model.MemberDTO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Map<K,V> : Key ,Value 로 구성되어있는 새로운 자료 구조
	private Map<String, iCommand> map;
	@Override
	public void init() throws ServletException {
		// 서버가 실행할 때 특정값들을 초기화해주는 메소드 ( 단 한번만 실행됨 )
		map = new HashMap<String, iCommand>();
		map.put("/LoginCon.do" , new LoginCon());
		map.put("/JoinCon.do" , new JoinCon());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		// System.out.println(requestURI);
		// System.out.println(contextPath);
		System.out.println("들어온 요청 : " + command);
		
		// "/LoginCon.do" 요청이 들어왔을때
		// iCommand com = new LoginCon();
		// iCommand com = map.get("/LoginCon.do"); 처럼 처리됨
		iCommand com = map.get(command);
		com.execute(request, response);
		
		
//		// 컨트롤러 변경하면서 전부 필요없음
//		// 이메일, 비밀번호, 전화번호, 주소
//		String email = request.getParameter("email");
//		String pw = request.getParameter("pw");
//		String tel = request.getParameter("tel");
//		String address = request.getParameter("address");
//		
//		MemberDAO dao = new MemberDAO();
//		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		if ( command.equals("/LoginCon.do")) {
////			// 로그인 처리
////			MemberDTO member = dao.memberLogin(email, pw);
////			
////			if (member != null) {
////				System.out.println("로그인 성공!");
////				
////				HttpSession session = request.getSession();
////				session.setAttribute("member", member);
////				response.sendRedirect("main.jsp");
////			} else {
////				out.print("<script>");
////				out.print("alert('로그인 실패..!');");
////				out.print("location.href = 'main.jsp';");
////				out.print("</script>");
////			}
//			// 로그인 처리
//			LoginCon login = new LoginCon();
//			login.execute(request, response);
//		} else if (command.equals("/JoinCon.do")) {
//			// 회원가입 처리
////			int cnt = dao.memberJoin(new MemberDTO(email, pw, tel, address));
////			
////			if (cnt > 0) {
////				request.setAttribute("success_data", email);
////				RequestDispatcher dispatcher = request.getRequestDispatcher("join_success.jsp");
////				dispatcher.forward(request, response);
////			} else {
////				out.print("<script>");
////				out.print("alert('회원가입 실패..!');");
////				out.print("location.href = 'main.jsp';");
////				out.print("</script>");
////			}
//			// 회원가입 처리
//			JoinCon join = new JoinCon();
//			join.execute(request, response);
//		}
	}

}
