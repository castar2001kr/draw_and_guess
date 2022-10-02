package game.repository.player;

import java.io.IOException;

import javax.websocket.Session;

public class Player {
	
	private Session session;
	
	int pid;
	
	String name;
	
	String id;
	
	int lv;
	
	int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	public int getPid() {
		return this.pid;
	}
	
	public void setPid(int pid) {
		this.pid=pid;
	}
	
	public boolean emitMsg(String msg) {
		
		synchronized(this) {
			
			try {
				this.session.getBasicRemote().sendText(msg);
				return true;
			} catch (IOException e) {
				
				return false;
			}
		}
		
	}
	
	public void plusScore(int s) {
		this.score+=s;
	}
	
}
