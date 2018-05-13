package com.vio.sockets.decoders;
import com.vio.sockets.handler.CustomMessageHandler;
import com.vio.sockets.model.Message;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
        
        
        JsonArrayBuilder array = Json.createArrayBuilder();
        
        CustomMessageHandler
                .connectedSessions()
                .forEach(user -> array.add(user));
        
        JsonArray json_array = array.build();
        
        JsonObjectBuilder json = Json
                .createObjectBuilder()
                .add("message", (message.getUsername() + ": " + message.getMessage()))
                .add("users", json_array);
                          
        StringWriter writer = new StringWriter();
        
        try(JsonWriter jsonWriter = Json.createWriter(writer)) { jsonWriter.writeObject(json.build()); }
        LOG.log(Level.INFO, "Encoding {0}", writer.toString());
        
        return writer.toString();
    }
    @Override
    public void init(EndpointConfig config) {LOG.log(Level.INFO, "Calling the init method from MessageEncoder{0}", config.getUserProperties().get("username")); }
    @Override
    public void destroy() {LOG.info("Calling the destroy method from MessageEncoder");} 
}
