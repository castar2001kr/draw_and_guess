package game.repository.ActRouter;

import game.repository.manager.Room;
import game.repository.player.Player;
import game.repository.process.Process0;
import game.repository.process.Process1;
import game.repository.process.Process2;

public class Router012 {

	private final ActEntrance ent;
	
	private final Room room;
	
	private Process0 pro0;
	private Process1 pro1;
	private Process2 pro2;
	
	
	private boolean pstate = false;
	
	public Router012(ActEntrance ent) {
		
		this.ent=ent;
		this.room=ent.getRoom();
		
		this.pro1=new Process1(room);
		this.pro2=new Process2(room);
		
	}
	
	synchronized public void play(String ans) {
		
		if(!pstate) {//play draw; 
			this.pro0=new Process0(this.room, ans); //make Process0
			pstate=true;
			
		}
	}
	
	
	synchronized public void answer(String msg, int pid) {
		
		if(pstate) {
			
			boolean corrected = pro0.emit_0(msg, 0); //player corrected the answer
			
			if(corrected) {
				
				pro1.emit_1(); //stop;
				this.pstate=false;
			}
			
		}
		
	}
	
	synchronized public boolean enter(Player p) {
		
		if(pro2.emit_0(p)) {
			return true;
		}
		return false;
	}
	
	synchronized public void out(Player p) {
		
		if(pro2.emit_1(p)) {
			
			
			pro1.emit_1(); //stop
			this.pstate=false;
		}
		
	}
	
	
	
}
