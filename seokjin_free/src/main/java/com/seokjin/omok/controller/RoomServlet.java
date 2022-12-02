package com.seokjin.omok.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seokjin.omok.domain.RoomVO;
import com.seokjin.omok.domain.UserVO;
import com.seokjin.omok.persistence.RoomDAO;
import com.seokjin.omok.service.RoomService;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final RoomService roomService = new RoomService(new RoomDAO());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmdReq = request.getParameter("cmd");

		HttpSession session = request.getSession();
		
		if(cmdReq.equals("roomlist")) {
			ArrayList<RoomVO> rooms = roomService.getRooms();
			request.setAttribute("rooms", rooms);
			RequestDispatcher view = request.getRequestDispatcher("view/game_room_list.jsp");
			view.forward(request, response);
		}else if(cmdReq.equals("openroom")) {
			if(session.getAttribute("user") == null) {
				response.sendRedirect("view/sign_in.jsp");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("view/open_room.jsp");
				view.forward(request, response);
	
			}						
		}else if(cmdReq.equals("enterroom")) {
			if(session.getAttribute("user") == null) {
				response.sendRedirect("view/sign_in.jsp");
			}else {
				int room_num = Integer.parseInt(request.getParameter("num"));
				RoomVO room = roomService.getRoom(room_num);
				request.setAttribute("room", room);
				RequestDispatcher view = request.getRequestDispatcher("view/game_room.jsp");
				view.forward(request, response);
			}
		}else if(cmdReq.equals("deleteroom")) {
			int room_num = Integer.parseInt(request.getParameter("num"));
			String founder = request.getParameter("founder");
			UserVO user = (UserVO)session.getAttribute("user");
			if(user != null && user.getUsername().equals(founder)) roomService.deleteRoom(room_num);
			ArrayList<RoomVO> rooms = roomService.getRooms();
			request.setAttribute("rooms", rooms);
			RequestDispatcher view = request.getRequestDispatcher("view/game_room_list.jsp");
			view.forward(request, response);
		}else if(cmdReq.equals("singleGameRoom")) {
			response.sendRedirect("view/single_play.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cmdReq = request.getParameter("cmd");
		RoomVO room = new RoomVO();
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		if(cmdReq.equals("openroom")) {
			room.setFounder(user.getUsername());
			room.setTitle(request.getParameter("title"));
			roomService.join(room);
			ArrayList<RoomVO> rooms = roomService.getRooms();
			request.setAttribute("rooms", rooms);
			RequestDispatcher view = request.getRequestDispatcher("view/game_room_list.jsp");
			view.forward(request, response);
		}else if(cmdReq.equals("updateroom")) {
			int room_num = Integer.parseInt(request.getParameter("num"));
			room = roomService.getRoom(room_num);
			room.setTitle(request.getParameter("title"));
			roomService.modifyRoom(room);
			request.setAttribute("room", room);
			RequestDispatcher view = request.getRequestDispatcher("view/game_room.jsp");
			view.forward(request, response);
	
		}
	}

}
