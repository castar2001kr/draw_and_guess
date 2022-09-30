package game.repository.manager;

import java.util.Queue;
import java.util.Stack;

import game.repository.idgenerator.IdGenerator;
import game.repository.idgenerator.Info;
import game.repository.info.Player;
import member.dto.MemberDTO;

public class Room {
	
	
	private final IdGenerator<Player> idGen;
	private static final int SIZE = 6;
	
	private final String title;
	private Player host;
	
	private final GameManager manager;
	
	public String getTitle() {
		return this.title;
	}
	
	
	public Room(String title, Player host){
		
		manager=new GameManager();
		
		idGen=new IdGenerator<Player>(SIZE);
		this.title=title;
		this.host=host;
	}
	
	public boolean enter(Player p) {
		
		
		int pid = idGen.getID();
		
		if(pid<0) {
			return false;
		}
		
		p.setPid(pid);
		
		Info<Player> i=new Info<Player>();
		
		i.setInfo(p);
		
		idGen.set(pid,i);
		
		return true;
		
	}
	
	
	public boolean out(Player p) {
		
		idGen.erase(p.getPid());
		
		return true;
	}
	
	public Queue<Integer> getPlayers() {
		
		return this.idGen.getAll();
		
	}
	
	public Player getHost() {
		return host;
	}
	
	
	public GameManager gameManager() {
		return this.manager;
	}
	
	
	public String to_manager(String msg) {
		
		synchronized(manager) {
			
			String result=null;
			
			//msg to manager
			//manager.emitMsg(msg);
			
			return result;
		}
		
	}
	

}
