/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.decoders;

import com.vio.sockets.model.UserConnected;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author arito
 */
public class UserConnectedDecoder implements Encoder.Text<UserConnected> {

    @Override
    public String encode(UserConnected arg0) throws EncodeException {
        JsonObject object = Json.createObjectBuilder()
                .add("notification", arg0.getNotification())
                .build();
        
        return object.toString();
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
        
    }
    
}
