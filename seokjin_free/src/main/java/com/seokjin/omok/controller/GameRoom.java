package com.seokjin.omok.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/GameRoom/{num}")
public class GameRoom {
	// 접속 된 클라이언트 WebSocket session 관리 리스트
	private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("num") String num){
		sessionUsers.add(userSession);
		System.out.println("client is now connected...");
	}
	//WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message) {
		System.out.println("receive from client : " + message);

		sessionUsers.forEach(session ->{
			try {
				session.getBasicRemote().sendText(message);
			}catch(IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	//WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session userSession) {
		System.out.println("client is now disconnected...");
		sessionUsers.remove(userSession);
	}
	// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}