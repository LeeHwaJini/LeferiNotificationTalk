package com.leferi.notification.exception;


public class InvalidArgumentException extends RuntimeException{
	
	    private static final long serialVersionUID = 4510772616712096788L;
	    
	    public InvalidArgumentException() {
	    	
	    }
	    public InvalidArgumentException(String message) {
	    	super(message);
	    }
		
	    public long getErrorCode() {
	        return 100;
	    }

	   
	    public String getErrorMsg() {
	        return "잘못된 정보가 입력되었습니다.";
	    }
	
	    
}
