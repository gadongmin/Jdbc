package com.javaex.ex05;

public class BookApp {

	public static void main(String[] args) {
		BookDAO bookDAO = new BookDAO();

		// int b01 = bookDAO.bookInsert("무빙", "재미주의", "2013-03-04");
		// int b02 = bookDAO.bookUpdate("패션왕", "중앙북스", "2012-02-22", 5);
		// int b03 = bookDAO.bookDelete(8);
		/*
		List<BookVO> bookList = bookDAO.bookSelect();
		for (int i = 0; i < bookList.size(); i++) {
			BookVO bookVO = bookList.get(i);
			System.out.println(bookVO.getBookId() + ".   " + bookVO.getTitle() + "   " 
							   + bookVO.getPubs() + "   " + bookVO.getPubData());
	    }
	   */
		
		BookVO bookVO = bookDAO.bookSelectOne(2);
		System.out.println(bookVO);
		
	}

}
