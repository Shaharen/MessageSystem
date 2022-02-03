package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private int cnt;
	private String sql;

	// DB 연결
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";

			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DB 연결 종료
	public void close() {
		try {
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

	// 메시지 전송
	public int sendMessage(MessageDTO message) {

		connect();

		sql = "insert into web_message values (num_seq.nextval, ?, ?, ?, sysdate)";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, message.getM_sendName());
			psmt.setString(2, message.getM_receiveEmail());
			psmt.setString(3, message.getM_content());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// 메시지 검색
	public ArrayList<MessageDTO> messageSelect(String email) {

		connect();

		sql = "select m_num, m_sendName, m_content, m_sendDate from web_message where m_receiveEmail = ?";

		ArrayList<MessageDTO> list = new ArrayList<MessageDTO>();
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, email);

			rs = psmt.executeQuery();

			while (rs.next()) {

//				이메일은 필요없으니 null처리
//				1. list.add(new MessageDTO(rs.getInt(1), rs.getString(2), null, rs.getString(3), rs.getString(4)));

				// 2.
				int num = rs.getInt("m_num");
				String sendName = rs.getString("m_sendName");
				String content = rs.getString("m_content");
				String sendDate = rs.getString("m_sendDate");

				MessageDTO message = new MessageDTO(num, sendName, null, content, sendDate);
				list.add(message);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 메시지 삭제
	public int messageDelete(String email, String num) {

		connect();

		sql = "delete from web_message where m_receiveEmail = ? and m_num = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, email);
			psmt.setString(2, num);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}
	// 메시지 전체 삭제
	public int messageDeleteAll(String email) {

		connect();

		sql = "delete from web_message where m_receiveEmail = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, email);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
}
