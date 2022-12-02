package com.seokjin.omok.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.seokjin.omok.domain.UserVO;
import com.seokjin.omok.persistence.UserDAO;
import com.seokjin.omok.service.UserService;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/UserRestServlet")
public class UserRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService(new UserDAO());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String cmdReq = request.getParameter("cmd");
		if(cmdReq == null) return;
	
		JSONArray arrayJson = new JSONArray();
		
		if(cmdReq.equals("list")) {
			try {
				List<UserVO> users = userService.getUsers();
				for(UserVO user: users) {
					JSONObject json = new JSONObject();
					json.put("username", user.getUsername());
					json.put("password", user.getPassword());
					json.put("email", user.getEmail());
					json.put("win", user.getWin());
					json.put("loss", user.getLoss());
					json.put("win_loss_ratio", user.getWin_loss_ratio());
					json.put("message", user.getMessage());
					arrayJson.put(json);
				}
			}catch(JSONException e) {
				e.printStackTrace();
			}
			out.print(arrayJson);
		}else if(cmdReq.equals("read")) {

			try {
				String username = request.getParameter("username");
				if(username == null) {
					out.print("닉네임을 확인하세요");
					return;
				}
				UserVO user = userService.getUser(username);
				if(user.getUsername() == null) {
					out.print("닉네임을 확인하세요");
					return;
				}
				JSONObject json = new JSONObject();
				json.put("username", user.getUsername());
				json.put("password", user.getPassword());
				json.put("email", user.getEmail());
				json.put("win", user.getWin());
				json.put("loss", user.getLoss());
				json.put("win_loss_ratio", user.getWin_loss_ratio());
				json.put("message", user.getMessage());
				out.print(json);
				
			}catch(JSONException e) {
				e.printStackTrace();
			}
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
