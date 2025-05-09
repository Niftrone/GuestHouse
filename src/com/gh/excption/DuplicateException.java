package com.gh.excption;

public class DuplicateException extends RuntimeException{
	public DuplicateException() {
		this("중복된 값이있습니다.");
	}
	
    public DuplicateException(String message) {
        super(message);
    }
}
