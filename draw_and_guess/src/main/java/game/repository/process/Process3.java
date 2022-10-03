package game.repository.process;

import java.util.Queue;

import game.repository.manager.Room;

public class Process3 {
	
	private final Room room;
	
	public Process3(Room room) {
		
		this.room=room;
	}

	
	public void emit_0(String msg){ //chat msg
		
		sendAll(msg);
		
	}
	
	private void sendAll(String msg) {
		
		Queue<Integer> q = room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().propMsg(msg);
			
		}
		
	}
	
}
