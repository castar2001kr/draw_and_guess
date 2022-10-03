package socket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

// 세션을 통하여 Player설정, OnOpen에 따라 MsgSubmit이 Interval하게 작동하도록 처리
// 메시지 핸들러는 Msg라우터를 통하여 처리되게 한다.
// OnCLose에 따라 MsgSubmit을 하는 타이머 태스크가 동작을 멈추도록 한다.


@ServerEndpoint("/game.io") 
public class WebSocket {

	@OnOpen
	public void handleOpen() {
		
	}
	
	@OnMessage
	public void handleMsg() {
		
	}
	
	@OnClose
	public void handleClose() {
		
	}
	
	@OnError
	public void handleError() {
		
	}
	
	
}
