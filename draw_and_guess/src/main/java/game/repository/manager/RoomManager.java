package game.repository.manager;

import java.util.Queue;

import game.repository.idgenerator.IdGenerator;
import game.repository.idgenerator.Info;
import game.repository.player.Player;

public class RoomManager {

	private static final RoomManager manager=new RoomManager();

	private final IdGenerator<Room> idGen;
	private static final int SIZE = 100;
	
	
	
	
	private RoomManager() {
		
		this.idGen = new IdGenerator<Room>(SIZE);
		
	}
	
	public static RoomManager getInstance() {
		
		return manager;
	}
	
	public int makeRoom(Player p, String title) {
		
		int rid = this.idGen.getID();
		
		if(rid<0) {
			return -1;
		}

		Info<Room> info = new Info<Room>();
		
		synchronized(info){
			
			Room r = new Room(title,rid);
			info.setInfo(r);
			r.enter(p);
			this.idGen.set(rid,info);
			info.setState(true);
			return rid; 
			
		}
		
	}
	
	public void clearRoom(int rid) {
		
		idGen.erase(rid);
		
	}
	
	public Queue<Integer> getRooms() {
		
		return this.idGen.getAll();
		
	}
	
	public Info<Room> getRoom(int rid){
		
		
		return this.idGen.get(rid);
		
	}
	
	
	
	
	
}
