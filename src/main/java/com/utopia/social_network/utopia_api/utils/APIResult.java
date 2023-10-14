/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author trita
 */
@Component
public class APIResult {
    public int Status;
    public String Message;
    public String Exception;
    public Object Data;

    public APIResult() {
    }

    public APIResult(int Status, String Message, String Exception, Object Data) {
        this.Status = Status;
        this.Message = Message;
        this.Exception = Exception;
        this.Data = Data;
    }
    
    public APIResult Success(Object data){
        return new APIResult(200,"Ok",null,data);
    }
    
    public APIResult MessageSuccess(String message,Object data){
        return new APIResult(200, message, null, data);
    }
}
