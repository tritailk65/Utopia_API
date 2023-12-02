/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.utils;

import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author trita
 */
public class ErrorDetails {
    public int Status;
    public String Exception;
    public String Message;
    public String Path;

    public ErrorDetails(int Status, String Exception, String Message, String Path) {
        this.Status = Status;
        this.Exception = Exception;
        this.Message = Message;
        this.Path = Path;
    }
}
