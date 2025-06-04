package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		// 리스트 생성
		List<AuthorVO> aList = new ArrayList<AuthorVO>();
				
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
			query += " select author_id  ";
			query += " 		  ,author_name ";
			query += " 		  ,author_desc ";
			query += " from   author ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			// 리스트 형태로 담아서 출력
			while(rs.next()) {
				// Result의 데이터를 자바의 변수에 담는다.
				int authorId =  rs.getInt("author_id");
			 // int authorId =  rs.getInt(1);
				String authorName = rs.getString("author_name");
			 // String authorName = rs.getString(2);
				String authorDesc = rs.getString("author_desc");
			 // String authorDesc = rs.getString(3);
				
				// 자바의 데이터를 VO객체로 묶는다.
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);
				
				// VO를 리스트에 추가한다.
				aList.add(authorVO);
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
		/*
		for(int i = 0; i<aList.size(); i++) {
			System.out.println(aList.get(i).toString());
		}
		*/
		
		// System.out.println(aList.get(0).getAuthorName());
		
		for(int i = 0; i<aList.size(); i++) {
			aList.get(i).showInfo();
		}
		

	}

}
