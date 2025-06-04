package com.javaex.ex02;

public class BookAuthorVO {
	// 필드
	private int bookId;
	private String title;
	private String pubs;
	private String pubData;
	private int authorId;
	private String authorName;
	private String authorDesc;

	// 생성자
	public BookAuthorVO() {}

	public BookAuthorVO(int bookId, String title, String pubs, String pubData, int authorId, String authorName,
			String authorDesc) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubData = pubData;
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	// 메소드 gs
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubData() {
		return pubData;
	}

	public void setPubData(String pubData) {
		this.pubData = pubData;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "BookAuthorVO [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubData=" + pubData
				+ ", authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}

}
