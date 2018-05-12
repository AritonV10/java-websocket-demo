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
public class UserDisconnected extends AbstractDomain{
    
    private String notification;

    public UserDisconnected(String notification) {
        this.notification = notification;
    }
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
    
    
}
