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
	// Map<K,V> : Key ,Value �� �����Ǿ��ִ� ���ο� �ڷ� ����
	private Map<String, iCommand> map;
	@Override
	public void init() throws ServletException {
		// ������ ������ �� Ư�������� �ʱ�ȭ���ִ� �޼ҵ� ( �� �ѹ��� ����� )
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
		System.out.println("���� ��û : " + command);
		
		// "/LoginCon.do" ��û�� ��������
		// iCommand com = new LoginCon();
		// iCommand com = map.get("/LoginCon.do"); ó�� ó����
		iCommand com = map.get(command);
		com.execute(request, response);
		
		
//		// ��Ʈ�ѷ� �����ϸ鼭 ���� �ʿ����
//		// �̸���, ��й�ȣ, ��ȭ��ȣ, �ּ�
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
////			// �α��� ó��
////			MemberDTO member = dao.memberLogin(email, pw);
////			
////			if (member != null) {
////				System.out.println("�α��� ����!");
////				
////				HttpSession session = request.getSession();
////				session.setAttribute("member", member);
////				response.sendRedirect("main.jsp");
////			} else {
////				out.print("<script>");
////				out.print("alert('�α��� ����..!');");
////				out.print("location.href = 'main.jsp';");
////				out.print("</script>");
////			}
//			// �α��� ó��
//			LoginCon login = new LoginCon();
//			login.execute(request, response);
//		} else if (command.equals("/JoinCon.do")) {
//			// ȸ������ ó��
////			int cnt = dao.memberJoin(new MemberDTO(email, pw, tel, address));
////			
////			if (cnt > 0) {
////				request.setAttribute("success_data", email);
////				RequestDispatcher dispatcher = request.getRequestDispatcher("join_success.jsp");
////				dispatcher.forward(request, response);
////			} else {
////				out.print("<script>");
////				out.print("alert('ȸ������ ����..!');");
////				out.print("location.href = 'main.jsp';");
////				out.print("</script>");
////			}
//			// ȸ������ ó��
//			JoinCon join = new JoinCon();
//			join.execute(request, response);
//		}
	}

}
