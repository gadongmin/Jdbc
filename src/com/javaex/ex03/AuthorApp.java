package com.javaex.ex03;

import java.util.List;

import com.javaex.ex01.AuthorVO;

// 메인프로그램
public class AuthorApp {

	public static void main(String[] args) {
		AuthorDAO authorDao = new AuthorDAO();
		/*
		int count =  authorDao.authorInsert("박태준", "웹툰작가");
		if(count > 0) {
			System.out.println("등록되었습니다.");
		}else if(count < 0) {
			System.out.println("오류");
		}else {
			System.out.println("등록되지 않았습니다.");
		}
		*/
		
		/*
		int count = authorDao.authorDelete(8);
		if(count > 0) {
			System.out.println("삭제되었습니다.");
		}else if(count < 0) {
			System.out.println("오류");
		}else {
			System.out.println("삭제되지 않았습니다.");
		}
		*/
		
		/*
		int count = authorDao.authorUpdata(3, "박태준", "웹툰작가");
		if(count > 0) {
			System.out.println("수정되었습니다.");
		}else if(count < 0) {
			System.out.println("오류");
		}else {
			System.out.println("수정되지 않았습니다.");
		}
		*/
		
		List<AuthorVO> authorList = authorDao.authorList();

		for (int i = 0; i < authorList.size(); i++) {
			AuthorVO authorVO = authorList.get(i);
			System.out.println(
					authorVO.getAuthorId() + ". " + authorVO.getAuthorName() + "(" + authorVO.getAuthorDesc() + ")");
		}
	}

}
