package com.snsprj.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MarcoHandler extends AbstractWebSocketHandler{

	private static final Logger logger = LoggerFactory.getLogger(MarcoHandler.class);
	
	protected void handleTextMessage(WebSocketSession session,TextMessage message) throws InterruptedException, IOException{
		logger.info("Received message:"+ message.getPayload());
		
		Thread.sleep(2000);
		
		session.sendMessage(new TextMessage("Polo!"));
	}
}
