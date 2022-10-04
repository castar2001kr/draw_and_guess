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
		
		String msg = "{h : 1, p : "+in+", a : 3, b : 0 }"; //JSON msg
		
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
		
		String msg = "{h : 1, p :"+ out +", a : 5, b : 0 }"; //JSON msg
		
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
		
		String msg="{h:1, b : {act:2,pid:"+room.getHostPid()+"}}"; //JSON msg. ajax 硫붿떆吏�媛� �굹以묒뿉 �룄李⑺븯�뒗 寃껋쓣 諛⑹��븯湲� �쐞�빐 msg瑜� 癒쇱� 諛쏆쑝硫� �겢�씪�씠�뼵�듃�뒗 ajax濡� �샇�뒪�듃 �꽕�젙 �씠�썑 �슂泥��빐�빞 �븳�떎.
		
		Queue<Integer> q = room.getPlayers();
		
		while(!q.isEmpty()) {
			
			room.getPlayer(q.poll()).getInfo().propMsg(msg);
		}
		
	}
	
	
	

}
