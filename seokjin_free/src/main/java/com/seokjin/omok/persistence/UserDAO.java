package com.seokjin.omok.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.seokjin.omok.domain.RoomVO;
import com.seokjin.omok.domain.UserVO;

public class UserDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook", "passwd");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public UserVO save(UserVO user) {
		connect();
		String sql = "insert into user values(?, ?, ?,? , ?, ? ,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getWin());
			pstmt.setInt(5, user.getLoss());
			pstmt.setDouble(6, user.getWin_loss_ratio());
			pstmt.setString(7, user.getMessage());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return user;
	}
	
	public void update(UserVO user) {
		connect();
		String sql = "update user set password=?, email=?, win=?, loss=?, win_loss_ratio = ?, message=? WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getWin());
			pstmt.setInt(4, user.getLoss());
			pstmt.setDouble(5, user.getWin_loss_ratio());
			pstmt.setString(6, user.getMessage());
			pstmt.setString(7, user.getUsername());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	public UserVO findByName(String name){
		connect();
		String sql = "SELECT * FROM user WHERE username=?";
		UserVO user = new UserVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setWin(rs.getInt("win"));
				user.setLoss(rs.getInt("loss"));
				user.setWin_loss_ratio(rs.getDouble("win_loss_ratio"));
				user.setMessage(rs.getString("message"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return user;
	}
	public ArrayList<UserVO> findAll(){
		connect();
		ArrayList<UserVO> users = new ArrayList<UserVO>();
		String sql = "select * from user";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setWin(rs.getInt("win"));
				user.setLoss(rs.getInt("loss"));
				user.setWin_loss_ratio(rs.getDouble("win_loss_ratio"));
				user.setMessage(rs.getString("message"));
				users.add(user);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return users;
	}
	
	
}
