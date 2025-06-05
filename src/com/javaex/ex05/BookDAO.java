package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.ex01.AuthorVO;

public class BookDAO {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	// 생성자
	public BookDAO() {
	}

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

	// -- 책 등록
	public int bookInsert(String title, String pubs, String pubDate) {
		int count = -1;

		this.connect();

		try {
			// - SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(null, ?, ?, ?) ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);

			// - 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	} // insert

	// -- 책 수정
	public int bookUpdate(String title, String pubs, String pubDate, int bookId) {
		int count = -1;

		this.connect();
		
		try {

			// SQL문 준비
			String query = "";
			query += " update book  ";
			query += " set title = ? ";
			query += "     ,pubs = ? ";
			query += "     ,pub_date = ? ";
			query += " where book_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, bookId);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // Updata

	// -- 팩 삭제
		public int bookDelete(int bookId) {
			int count = -1;

			this.connect();
			
			try {
				// SQL문 준비
				String query = "";
				query += " delete from	book ";
				query += " where book_id = ? ";

				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookId);

				// 실행
				count = pstmt.executeUpdate();


			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();
			
			return count;
		} // delete
		
		// -- 책 리스트
		public List<BookVO> bookSelect() {
			
			this.connect();

			// 리스트 생성
			List<BookVO> bookList = new ArrayList<BookVO>();

			try {
				// SQL문 준비
				String query = "";
				query += " select book_id ";
				query += " 		  ,title ";
				query += " 		  ,pubs ";
				query += " 		  ,pub_date ";
				query += " from   book ";

				// 바인딩
				pstmt = conn.prepareStatement(query);

				// 실행
				rs = pstmt.executeQuery();

				// 리스트 형태로 담아서 출력
				while (rs.next()) {
					// Result의 데이터를 자바의 변수에 담는다.
					int bookId = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pubDate = rs.getString("pub_date");

					// 자바의 데이터를 VO객체로 묶는다.
					BookVO bookVO = new BookVO(bookId, title, pubs, pubDate);

					// VO객체를 리스트에 추가한다.
					bookList.add(bookVO);
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			this.close();
			
			return bookList;
		} // select
		
		// -- 작가 검색
		public BookVO bookSelectOne(int bookId) {
			BookVO bookVO = null;

			this.connect();

			try {
				// SQL문 준비
				String query = "";
				query += " select book_id ";
				query += " 		  ,title ";
				query += " 		  ,pubs ";
				query += " 		  ,pub_date ";
				query += " from   book ";
				query += " where  book_id = ? ";

				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookId);

				// 실행
				rs = pstmt.executeQuery();

				rs.next();
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");

				bookVO = new BookVO(id, title, pubs, pubDate);

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();
			
			return bookVO;
		}// search
}

