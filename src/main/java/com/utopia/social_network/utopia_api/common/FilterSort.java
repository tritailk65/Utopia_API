/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.common;

import org.springframework.data.domain.Sort;

/**
 *
 * @author DELL
 */
public class FilterSort {
    public static Sort getDesc(String field) {
        return Sort.by(Sort.Direction.DESC, field);
    }
    
    public static Sort getAsc(String field) {
        return Sort.by(Sort.Direction.ASC, field);
    }
}
