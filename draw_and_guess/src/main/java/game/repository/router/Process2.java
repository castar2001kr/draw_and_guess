package game.repository.router;

import java.util.Queue;

import game.repository.manager.Room;
import game.repository.player.Player;

public class Process2 { //process enter, out, changeHost msg
	
	private final Room room;
	
	public Process2(Room r) {
		
		this.room=r;
	}
	

	public void emit_0(Player p) { //enter
		
		int in = p.getPid();
		
		String msg = "{h : 1, b : {act : 0, pid :"+in+"}}"; //JSON msg
		
		Queue<Integer> q=room.getPlayers();
		
		while(!q.isEmpty()) {
			
			int temp = q.poll();
			
			room.getPlayer(temp).getInfo().emitMsg(msg);
			
		}
		
	}
	
	
	public void emit_1(Player p) { //out
		
		
		int out = p.getPid();
		
		String msg = "{h : 1, b : {act : 1, pid :"+out+"}}"; //JSON msg
		
		Queue<Integer> q=room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().emitMsg(msg);
		}
		
	}
	
	
	public void emit_2() { //change host
		
		String msg="{h:1, b : {act:2,pid:"+room.getHostPid()+"}}"; //JSON msg
		
		Queue<Integer> q = room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().emitMsg(msg);
		}
		
	}
	
	
	

}
