package game.controller;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import game.repository.idgenerator.Info;
import game.repository.idgenerator.Node;
import game.repository.manager.Room;
import game.repository.manager.RoomManager;
import game.repository.player.Player;
import member.dto.MemberDTO;

/**
 * Servlet implementation class game
 */
@WebServlet(urlPatterns= {"/game/*"})
public class game extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public game() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		String pathInfo = request.getPathInfo();
		String[] paths=pathInfo.split("/");
		
		if(paths[1].equals("newroom")) {
			
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/game/newroom.html");
			dispatcher.forward(request, response);
			return;
			
		}
		
		if(paths[1].equals("roomlist")) { //roomlist 요청
			
			Queue<Node> q = RoomManager.getInstance().getRooms();
			
			JSONArray jarray = new JSONArray();
			
			while(!q.isEmpty()) {
				
				JSONObject obj = new JSONObject();
				obj.put("id", q.peek().getId());
				obj.put("name", q.peek().getName());
				jarray.add(obj.toJSONString());
			}
			
			response.getWriter().print(jarray.toJSONString());
		}else {
					// game/rid , roomname=?  // room 입장, 입장여부 확인
			try{
				
				String rn=(String) request.getAttribute("roomname");
				
				MemberDTO dto=(MemberDTO) request.getSession().getAttribute("memberDTO");
				
				int rid =Integer.parseInt(paths[1]);
				Player player  = new Player();
				player.setId(dto.getId());
				player.setLv(dto.getLv());
				player.setName(dto.getName());
				
				
				Info<Room> rinfo = RoomManager.getInstance().getRoom(rid);
				boolean checkval = false;
				
				if(rinfo.getInfo().getTitle()==rn) {
					
					checkval = rinfo.getInfo().enter(player);
					
					if(checkval) {
						
						request.getSession().setAttribute("state", true);
						request.getSession().setAttribute("player", player);
						
					}
				}
					
					
				response.getWriter().print(checkval);
								
				
				
				
			}catch(Exception e) {
				
				response.getWriter().print("잘못된 요청...");
			}
			
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pathInfo=request.getPathInfo();
		String[] paths = pathInfo.split("/");
		
		
		if(paths[1].equals("roomlist")) {				//roomlist에 올리기. 방 생성.
			
			MemberDTO dto=(MemberDTO) request.getSession().getAttribute("memberDTO");
			
			Player player  = new Player();
			player.setId(dto.getId());
			player.setLv(dto.getLv());
			player.setName(dto.getName());
			
			
			try {
				
				String title = (String) request.getParameter("title"); // post title=?
				
				int rid = RoomManager.getInstance().makeRoom(player, title);
				request.getSession().setAttribute("state", true);
				request.getSession().setAttribute("player", player);
				
				JSONObject jobj=new JSONObject();
				
				jobj.put("title", title);
				jobj.put("rid", rid);
				
				response.getWriter().print(jobj.toJSONString());
				
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
	}

}
