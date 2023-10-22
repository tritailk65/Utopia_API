/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author trita
 */
public class ErrorDetails {
    private int Status;
    private String Exception;
    private String Message;
    private Object Data;

    public ErrorDetails(int Status, String Exception, String Message, Object Data) {
        this.Status = Status;
        this.Exception = Exception;
        this.Message = Message;
        this.Data = Data;
    }
}
