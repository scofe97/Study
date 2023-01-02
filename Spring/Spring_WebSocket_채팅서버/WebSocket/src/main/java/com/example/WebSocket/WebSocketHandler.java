package com.example.WebSocket;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // 웹 소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        sessions.put(sessionId, session);

        Message message = Message.builder().sender(sessionId).receiver("all").build();
        message.newConnect();

        sessions.values().forEach(s -> {
            try{
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage(Utils.getString(message)));
                }
            }catch (Exception e){

            }
        });
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String sessionId = session.getId();
        Message message = Utils.getObject(textMessage.getPayload());
        message.setSender(session.getId());

        WebSocketSession receiver = sessions.get(message.getReceiver());
        sessions.values().forEach(s -> {
            try{
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage(Utils.getString(message)));
                }
            }catch (Exception e){

            }
        });

        if(receiver != null && receiver.isOpen()){
            receiver.sendMessage(new TextMessage(Utils.getString(message)));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();

        sessions.remove(sessionId);

        final Message message = new Message();
        message.closeConnect();
        message.setSender(sessionId);

        sessions.values().forEach(s -> {
            try{
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage(Utils.getString(message)));
                }
            }catch (Exception e){

            }
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
