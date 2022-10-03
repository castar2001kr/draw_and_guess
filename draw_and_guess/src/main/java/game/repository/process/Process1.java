package game.repository.process;

import java.util.Queue;

import game.repository.manager.Room;

public class Process1 {

	
	private final Room room;
	
	public Process1(Room room) {
		
		this.room = room;
	}
	
	public void emit_0() { //wait no use
		
		String msg = "{h : 1, b : {act : 0}}";
		sendAll(msg);
		
		
	}
	
	public void emit_1() { //play
		
		room.play();
		String msg = "{h : 1, b : {act : 1}}";
		sendAll(msg);
		
	}
	
	
	public void emit_2() { //stop game msg. if the client's sent this msg without notify of winner, it means that the game has been interrupted stop.
		
		room.stop();
		String msg = "{h : 1, b : {act : 2}}";
		sendAll(msg);
		
	}
	
	private void sendAll(String msg) {
		
		Queue<Integer> q = room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().emitMsg(msg);
			
		}
		
	}
	
	
}
