package game.controller;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.repository.idgenerator.Info;
import game.repository.manager.Room;
import game.repository.manager.RoomManager;
import game.repository.player.Player;
import member.dto.MemberDTO;

/**
 * Servlet implementation class controller
 */
@WebServlet("/make")
public class make extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public make() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		MemberDTO dto=(MemberDTO) request.getSession().getAttribute("memberInfo");
		
		Player player  = new Player();
		player.setId(dto.getId());
		player.setLv(dto.getLv());
		player.setName(dto.getName());
		
		
		try {
			
			String title = (String) request.getAttribute("title"); //post title=?
			
			int rid = RoomManager.getInstance().makeRoom(player, title);
			request.getSession().setAttribute("rid",rid);
			request.getSession().setAttribute("state", true);
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
