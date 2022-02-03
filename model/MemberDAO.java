package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private int cnt;
	private String sql;

	// 패키지명 .model 은 DB관련
	// DB 연결기능
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";

			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e1) {
			// 예외 발생
			// 1. OracleDriver 클래스가 해당 위치에 없는 경우(ojdbc6.jar 미포함)
			// 해결 : WEB-INF -> lib -> ojdbc6.jar 저장
			// 2. OracleDriver 경로가 오타인경우 ( 대소문자 구별 )
			e1.printStackTrace();
		} catch (SQLException e) {
			// DB연결 정보가 정확하지 않을 경우
			e.printStackTrace();
		}
	}

	// DB 연결 종료
	public void close() {
		try {
			// 역순으로 종료
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입기능
	public int memberJoin(MemberDTO member) {

		connect();

		sql = "insert into web_member values(?,?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getM_email());
			psmt.setString(2, member.getM_pw());
			psmt.setString(3, member.getM_tel());
			psmt.setString(4, member.getM_address());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			// 1. SQL문장이 잘못 작성되었을 경우
			// 2. psmt객체로 잘못된 인덱스값을 작성했을 경우
			// 3. 테이블이 없는 경우
			e.printStackTrace();
		} finally { // 무조건 실행
			close();
		}
		return cnt;
	}

	public MemberDTO memberLogin(String email, String pw) {

		connect();

		MemberDTO member = null;
		sql = "select m_tel, m_address from web_member where m_email = ? and m_pw = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();

			if (rs.next()) { // 회원 가입된 정보가 DB에 있는 경우
				// rs.getString(1 / 2) : m_tel / m_address
				// member = new MemberDTO(email, null, rs.getString(1), rs.getString(2));
				String tel = rs.getString(1);
				String address = rs.getString(2);
				member = new MemberDTO(email, null, tel, address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return member;
	}

	public int memberUpdate(MemberDTO memberDTO) {

		connect();

		sql = "update web_member set m_pw = ?, m_tel = ?, m_address = ? where m_email = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, memberDTO.getM_pw());
			psmt.setString(2, memberDTO.getM_tel());
			psmt.setString(3, memberDTO.getM_address());
			psmt.setString(4, memberDTO.getM_email());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	// 멤버 관리
	public ArrayList<MemberDTO> memberSelectAll() {

		connect();

		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();

		sql = "select m_email, m_tel, m_address from web_member";

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("m_email");
				String tel = rs.getString("m_tel");
				String address = rs.getString("m_address");

				if (!email.equals("admin")) { // 관리자 계정은 제외하고 저장
					MemberDTO Member = new MemberDTO(email, null, tel, address);
					list.add(Member);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
