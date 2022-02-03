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
		
		// members��� ��ü ������ select.jsp�� �����ϱ� ���� forward ��� Ȱ��
		// why? redirect�� �ؽ�Ʈ �����͸� ���� ������
		
		// session�� ������� �ʰ� request�� ����ϴ� ������?
		// -> ȸ������ ������ select.jsp������ ����� ���̱� ������ request������ ����
		RequestDispatcher dispatcher = request.getRequestDispatcher("select.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
