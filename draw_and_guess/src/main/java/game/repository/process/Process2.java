package game.repository.process;

import java.util.Queue;

import game.repository.manager.Room;
import game.repository.player.Player;

public class Process2 { //process enter, out, changeHost msg
	
	private final Room room;
	
	public Process2(Room r) {
		
		this.room=r;
	}
	

	public boolean emit_0(Player p) { //enter
		
		boolean check = room.enter(p);
		
		if(!check) {
			return false;
		}
		
		int in = p.getPid();
		
		String msg = "{h : 1, b : {act : 0, pid :"+in+"}}"; //JSON msg
		
		Queue<Integer> q=room.getPlayers();
		
		while(!q.isEmpty()) {
			
			int temp = q.poll();
			
			room.getPlayer(temp).getInfo().propMsg(msg);
			
		}
		
		return true;
		
	}
	
	
	public boolean emit_1(Player p) { //out
		
		boolean hostChanged=room.out(p);
		
		int out = p.getPid();
		
		String msg = "{h : 1, b : {act : 1, pid :"+out+"}}"; //JSON msg
		
		Queue<Integer> q=room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().propMsg(msg);
		}
		
		if(hostChanged) {
			emit_2();
			return true;
		}
		
		return false;
	}
	
	
	private void emit_2() { //change host event's occured by host has been out from room
		
		String msg="{h:1, b : {act:2,pid:"+room.getHostPid()+"}}"; //JSON msg. ajax 메시지가 나중에 도착하는 것을 방지하기 위해 msg를 먼저 받으면 클라이언트는 ajax로 호스트 설정 이후 요청해야 한다.
		
		Queue<Integer> q = room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().propMsg(msg);
		}
		
	}
	
	
	

}
