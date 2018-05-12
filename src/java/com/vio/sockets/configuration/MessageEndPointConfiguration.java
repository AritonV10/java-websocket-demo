/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.configuration;

import com.vio.sockets.decoders.MessageDecoder;
import com.vio.sockets.decoders.MessageEncoder;
import com.vio.sockets.decoders.UserConnectedDecoder;
import com.vio.sockets.decoders.UserDisconnectedEncoder;
import com.vio.sockets.decoders.UsernameTakenEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.websocket.Decoder;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Extension;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 *
 * @author arito
 */
public class MessageEndPointConfiguration implements ServerApplicationConfig{

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        Set<ServerEndpointConfig> serverEndPointConfigList = new HashSet<>();
        endpointClasses
                .stream()
                .filter((clazz) -> (clazz.equals(MessageEndPoint.class)))
                .forEachOrdered((Class<? extends Endpoint> clazz) -> {
                        ServerEndpointConfig serverEndPointConfig = ServerEndpointConfig
                        .Builder
                        .create(clazz, "/chat-endpoint")
                        .encoders(                               
                                Arrays.asList(
                                MessageEncoder.class,                                
                                UserConnectedDecoder.class,                                
                                UserDisconnectedEncoder.class,                             
                                UsernameTakenEncoder.class                             
                                )
                        )
                                
                        .decoders(Arrays.asList(MessageDecoder.class))
                        .build();
                        serverEndPointConfigList.add(serverEndPointConfig);
                        
        });  
        return serverEndPointConfigList;
    }
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return Collections.emptySet();
    }
      
}
