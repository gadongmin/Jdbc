package com.javaex.ex04;

import java.util.List;

import com.javaex.ex01.AuthorVO;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDAO = new AuthorDAO();
		
		// int c01 = authorDAO.authorInsert("송지효", "런닝맨");
		
		// int c02 = authorDAO.authorUpdata(12, "김종국", "런닝맨");
		
		// int c03 = authorDAO.authorDelete(12);
		
		/*
		List<AuthorVO> authorList = authorDAO.authorSelect();
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVO authorVO = authorList.get(i);
			System.out.println(
					authorVO.getAuthorId() + ". " + authorVO.getAuthorName() + "(" + authorVO.getAuthorDesc() + ")");
		}
		*/
		
		AuthorVO authorVO = authorDAO.authorSelectOne(2);
		System.out.println(authorVO);
		}
	}


