package com.jaccro.model;

public class APIResponse {
  
    private Integer code;
    private String message;
    private String data;

    public APIResponse(){}
    
    public APIResponse(Integer code, String message, String data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
  
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
