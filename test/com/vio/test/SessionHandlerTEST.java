/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.test;

import com.vio.sockets.sessionhandlers.SessionHandler;
import com.vio.sockets.sessionhandlers.SessionHandlerImpl;
import com.vio.sockets.sessionhandlers.SessionService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author arito
 */

@RunWith(MockitoJUnitRunner.class)
public class SessionHandlerTEST {
    
   
    
 
    
    public SessionHandlerTEST() {}  
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
    }

    /**
     *
     */
    @Test
    public void testSessionHandler(){
        
    }
}
