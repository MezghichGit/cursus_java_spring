package com.sip.exceptions;

public class ArticleNotFoundException extends RuntimeException{
	
	public ArticleNotFoundException() {}
	
	public ArticleNotFoundException(String msg) {
		super(msg);
	}
	
	public ArticleNotFoundException(Exception ex) {
		super(ex);
	}

}
