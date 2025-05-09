package com.gh.excption;

public class NotFoundException extends RuntimeException{
	public NotFoundException() {
		this("값이 없습니다.");
	}
	
    public NotFoundException(String message) {
        super(message);
    }
}