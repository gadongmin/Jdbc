package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorAll {

	public static void main(String[] args) {
		// 리스트 생성
		List<BookAuthorVO> baList = new ArrayList<BookAuthorVO>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select b.book_id  ";
			query += " 		  ,b.title ";
			query += " 		  ,b.pubs ";
			query += " 		  ,b.pub_data ";
			query += " 		  ,a.author_id  ";
			query += " 		  ,a.author_name ";
			query += " 		  ,a.author_desc ";
			query += " from   book b ";
			query += " join   author a ";
			query += "   on   b.author_id = a.author_id ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트 형태로 담아서 출력
			while (rs.next()) {
				int bookId = rs.getInt("b.book_id");
				String title = rs.getString("b.title");
				String pubs = rs.getString("b.pubs");
				String pubData = rs.getString("b.pub_data");
				int authorId = rs.getInt("a.author_id");
				String authorName = rs.getString("a.author_name");
				String authorDesc = rs.getString("a.author_desc");

				BookAuthorVO bookAuthorVO = new BookAuthorVO(bookId, title, pubs, pubData, authorId, authorName,
						authorDesc);

				baList.add(bookAuthorVO);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		for (int i = 0; i < baList.size(); i++) {
			System.out.println(baList.get(i).toString());
		}
	}
}
