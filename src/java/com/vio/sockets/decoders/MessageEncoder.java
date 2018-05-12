/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.decoders;
import com.vio.sockets.model.Message;
import java.io.StringWriter;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author arito
 */
public class MessageEncoder implements Encoder.Text<Message> {

    private static final Logger LOG = Logger.getLogger(MessageEncoder.class.getName());

    @Override
    public String encode(Message message) throws EncodeException {
        JsonObject json = Json
                .createObjectBuilder()
                .add("message", message.getMessage())
                .build();
        StringWriter writer = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(writer)) { jsonWriter.writeObject(json); }
        
        return writer.toString();    
    }
    @Override
    public void init(EndpointConfig config) {LOG.info("Calling the init method from MessageEncoder");}
    @Override
    public void destroy() {LOG.info("Calling the destroy method from MessageEncoder");} 
}
