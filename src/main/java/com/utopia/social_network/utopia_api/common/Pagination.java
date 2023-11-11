/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.common;

/**
 *
 * @author DELL
 */
public class Pagination {
    public static int pageSize = 2;
    
    public static int getSkip(int page) {
        return ( page - 1 ) * pageSize;
    }
}
