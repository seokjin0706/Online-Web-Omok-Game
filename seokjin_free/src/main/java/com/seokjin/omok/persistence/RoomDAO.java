package com.seokjin.omok.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.seokjin.omok.domain.RoomVO;

public class RoomDAO {
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
	
	public RoomVO save(RoomVO room) {
		connect();
		String sql = "insert into room (founder, visitor, title) values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room.getFounder());
			pstmt.setString(2, room.getVisitor());
			pstmt.setString(3, room.getTitle());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return room;
	}
	public void delete(int num) {
		connect();
		String sql = "DELETE FROM room WHERE num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	public void update(RoomVO room) {
		connect();
		String sql = "update room set founder=?, visitor=?, title=? WHERE num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room.getFounder());
			pstmt.setString(2, room.getVisitor());
			pstmt.setString(3, room.getTitle());
			pstmt.setInt(4, room.getNum());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	public RoomVO findByNum(int num){
		connect();
		String sql = "SELECT * FROM room WHERE num=?";
		RoomVO room = new RoomVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				room.setNum(rs.getInt("num"));
				room.setFounder(rs.getString("founder"));
				room.setVisitor(rs.getString("visitor"));
				room.setTitle(rs.getString("title"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return room;
	}
	public ArrayList<RoomVO> findAll(){
		connect();
		ArrayList<RoomVO> rooms = new ArrayList<RoomVO>();
		String sql = "select * from room";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				RoomVO room = new RoomVO();
				room.setNum(rs.getInt("num"));
				room.setFounder(rs.getString("founder"));
				room.setVisitor(rs.getString("visitor"));
				room.setTitle(rs.getString("title"));
				rooms.add(room);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return rooms;
	}
}
