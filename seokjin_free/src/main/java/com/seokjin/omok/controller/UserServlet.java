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

import com.seokjin.omok.domain.UserVO;
import com.seokjin.omok.persistence.UserDAO;
import com.seokjin.omok.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService(new UserDAO());
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		
		if(cmdReq.equals("join")) {
			RequestDispatcher view = request.getRequestDispatcher("view/sign_up.jsp");
			view.forward(request, response);
		}else if(cmdReq.equals("signin")) {
			if(session.getAttribute("user") == null) {
				response.sendRedirect("view/sign_in.jsp");
			}else {
				response.sendRedirect("view/mypage.jsp");
			}
		}else if(cmdReq.equals("signout")) {
			session.invalidate();
			response.sendRedirect("view/sign_in.jsp");
			
		}else if(cmdReq.equals("ranking")) {
			ArrayList<UserVO> users = new ArrayList<UserVO>();
			users = userService.getUsers();
			request.setAttribute("users", users);
			RequestDispatcher view = request.getRequestDispatcher("view/ranking.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String message = "";
		String cmdReq = request.getParameter("cmd");
		UserVO user = new UserVO();
		HttpSession session = request.getSession();
		if(cmdReq.equals("join")) {
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setWin(0);
			user.setLoss(0);
			user.setWin_loss_ratio(0.0f);
			user.setMessage("Hello!");
			if(userService.join(user).equals("")) {
				message = "이미 존재하는 회원입니다.";
			}
			else {
				message = user.getUsername() + "님 회원가입을 축하드립니다.";
			}
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("view/sign_up_result.jsp");
			view.forward(request, response);
			
		}else if(cmdReq.equals("signin")) {
			if(userService.signin(request.getParameter("username"), request.getParameter("password"))) {
				session.setAttribute("user", userService.getUser(request.getParameter("username")));
				response.sendRedirect("view/mypage.jsp");
			}
			else {
				response.sendRedirect("view/sign_in.jsp");
			}
		}else if(cmdReq.equals("updatemypage")) {
			System.out.println(request.getParameter("username"));
			user = userService.getUser(request.getParameter("username"));
			user.setMessage(request.getParameter("message"));
			
			userService.modifyUser(user);
			session.setAttribute("user", userService.getUser(request.getParameter("username")));
			response.sendRedirect("view/mypage.jsp");
		}
	}

}
