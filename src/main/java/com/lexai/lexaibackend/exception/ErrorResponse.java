package com.lexai.lexaibackend.exception;

import java.time.LocalDateTime;


public class ErrorResponse {
    private int status;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    public ErrorResponse(int data, String error, String message){
        this.status=status;
        this.error=error;
        this.message=message;
        this.timestamp=LocalDateTime.now();

    }
}
