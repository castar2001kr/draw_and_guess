package game.repository.MsgRouter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.repository.ActRouter.ActEntrance;

public class MsgEntrance {
	
	private MsgRouter[] r;
	private final ActEntrance ent;
	
	public MsgEntrance(ActEntrance ent) {
		// TODO Auto-generated constructor stub
		
		this.ent =ent;
		
		r=new MsgRouter[7];
		r[0]=new MsgRouter() {

			@Override
			public void submit(String msg, int pid) {
				
				ent.answer(msg, pid);
				ent.chat(msg);
			}
			
		};
		
		r[5]=new MsgRouter() {
			@Override
			public void submit(String msg, int pid) {
				// TODO Auto-generated method stub
				ent.chat(msg);
				
			}
		};
		
		r[3]=new MsgRouter() {
			@Override
			public void submit(String msg, int pid) {
				// TODO Auto-generated method stub
			
				ent.draw(msg, pid);
				
			}
			
			
		};
		
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public void saveMsg(String msg, int pid) {
		
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj=(JSONObject) parser.parse(msg);
			
			Long head=(Long)obj.get("h"); // head
			Long pidRecived=(Long)obj.get("p"); //pid
			
			if(pidRecived.equals(pid)) {
								
				this.r[pid].submit(msg, pid);
				
			}
			
			
//			msgFormat= {
//					
//					h : 0, p : 0, a : 0, b : 0
//			}
//			
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block

		}
		
	}

}
