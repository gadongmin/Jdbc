package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.ex01.AuthorVO;

public class AuthorDAO {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	// 생성자
	public AuthorDAO() {}
	
	// 메소드 gs

	// 메소드 일반
	// -- db 연결
	private void connect() { // 메인에서 사용하지 못하게 private로 설정
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	// -- db 자원정리
	private void close() {
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
	
	
	// -- 작가 등록
	public int authorInsert(String name, String desc) {
		int count = -1;
		
		this.connect();
		
		try {
			// - SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);

			// - 실행
			count = pstmt.executeUpdate();


		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	
		this.close();

		return count;
	} // insert

	
	// -- 작가 수정
	public int authorUpdata(int authorId, String name, String desc) {
		int count = -1;

		this.connect();
		
		try {

			// SQL문 준비
			String query = "";
			query += " update author  ";
			query += " set author_name = ? ";
			query += " ,author_desc = ? ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, authorId);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // Updata

	
	// -- 작가 삭제
	public int authorDelete(int authorId) {
		int count = -1;

		this.connect();
		
		try {
			// SQL문 준비
			String query = "";
			query += " delete from	author ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// 실행
			count = pstmt.executeUpdate();


		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		
		return count;
	} // delete
	
	
	// -- 작가 리스트
	public List<AuthorVO> authorSelect() {
		
		this.connect();

		// 리스트 생성
		List<AuthorVO> authorList = new ArrayList<AuthorVO>();

		try {
			// SQL문 준비
			String query = "";
			query += " select author_id ";
			query += " 		  ,author_name ";
			query += " 		  ,author_desc ";
			query += " from   author ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 리스트 형태로 담아서 출력
			while (rs.next()) {
				// Result의 데이터를 자바의 변수에 담는다.
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				// 자바의 데이터를 VO객체로 묶는다.
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);

				// VO객체를 리스트에 추가한다.
				authorList.add(authorVO);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return authorList;
	} // select
	
	// -- 작가 검색
	public AuthorVO authorSelectOne(int authorId) {
		AuthorVO authorVO = null;

		this.connect();

		try {
			// SQL문 준비
			String query = "";
			query += " select author_id";
			query += " 		  ,author_name ";
			query += " 		  ,author_desc ";
			query += " from   author ";
			query += " where  author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// 실행
			rs = pstmt.executeQuery();

			rs.next();
			int id = rs.getInt("author_id");
			String name = rs.getString("author_name");
			String desc = rs.getString("author_desc");

			authorVO = new AuthorVO(id, name, desc);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		
		return authorVO;
	}// search
}

