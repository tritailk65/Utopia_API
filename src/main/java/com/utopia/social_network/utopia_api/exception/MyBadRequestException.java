/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author trita
 */
@ControllerAdvice
public class MyBadRequestException extends RuntimeException {

    public MyBadRequestException() {
        super();
    }
    
    public MyBadRequestException(final String message) {
        super(message);
    }
}

