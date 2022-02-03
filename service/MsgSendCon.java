package com.message.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.message.model.MessageDAO;
import com.message.model.MessageDTO;

@WebServlet("/MsgSendCon")
public class MsgSendCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		
		// 메인에서 입력받은 정보를 web_message에 저장
		MessageDAO dao = new MessageDAO();
		int cnt = dao.sendMessage(new MessageDTO(0, name, email, message, null)); 
		
		// 저장 후 메인으로 이동
		if (cnt>0) {
			response.sendRedirect("main.jsp");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('메시지 전송 실패..!');");
			out.print("location.href = 'main.jsp';");
			out.print("</script>");
		}		
	}

}
