package com.seokjin.omok.service;

import java.util.ArrayList;

import com.seokjin.omok.domain.RoomVO;
import com.seokjin.omok.persistence.RoomDAO;

public class RoomService {
	private final RoomDAO dao;
	public RoomService(RoomDAO dao) {
		this.dao = dao;
	}
	public int join(RoomVO room) {
		
		dao.save(room);
		return room.getNum();
	}
	public ArrayList<RoomVO> getRooms(){
		return dao.findAll();
	}
	
	public RoomVO getRoom(int num) {
		return dao.findByNum(num);
	}
	public void deleteRoom(int num) {
		dao.delete(num);
	}
	public void modifyRoom(RoomVO room) {
		dao.update(room);
	}
}
