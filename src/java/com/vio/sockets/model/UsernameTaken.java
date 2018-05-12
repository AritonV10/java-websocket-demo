/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.model;

/**
 *
 * @author arito
 */
public class UsernameTaken extends AbstractDomain {
    
    private String username;

    public UsernameTaken(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
