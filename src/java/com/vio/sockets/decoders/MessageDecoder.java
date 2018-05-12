/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.decoders;

import com.vio.sockets.model.Message;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import netscape.javascript.JSObject;



/**
 *
 * @author arito
 */
public class MessageDecoder implements Decoder.Text<Message>{

    private static final Logger LOG = Logger.getLogger(MessageDecoder.class.getName());

    
    @Override
    public Message decode(String jsonObject) throws DecodeException {
        /*
        JsonObject json = Json
                .createReader(new StringReader(jsonObject))
                .readObject();

         */
        JsonReader json = Json.createReader(new StringReader(jsonObject));
        JsonObject json_object = json.readObject();
        json.close();
        
        LOG.log(Level.INFO, "Receiving {0}", json_object.getString("message"));
        Message message = new Message(json_object.getString("message")); 
        
        return message;   
    }
    @Override
    public boolean willDecode(String arg0) {
        return true;
    }
    @Override
    public void init(EndpointConfig config) {
       LOG.info("inside decoder init");
    }

    @Override
    public void destroy() {
        LOG.info("inside decoder destroy method");
    }

   
    
}
