package com.seokjin.omok.service;

import java.util.ArrayList;

import com.seokjin.omok.domain.UserVO;
import com.seokjin.omok.persistence.UserDAO;

public class UserService {
	private final UserDAO dao;
	
	public UserService(UserDAO dao) {
		this.dao = dao;
	}
	
	public String join(UserVO user) {
		try {
			//같은 닉네임이 있는 중복 유저X	
			validateDuplicateMember(user);
			dao.save(user);
			return user.getUsername();
		}catch(IllegalStateException e) {
			e.printStackTrace();
			return "";
		}
	}
	public boolean signin(String username, String password) {

		UserVO user = dao.findByName(username);
		if(user.getUsername() ==null) return false;
		if(user.getPassword().equals(password)) return true;
		return false;
	}
	public ArrayList<UserVO> getUsers(){
		return dao.findAll();
	}
	public UserVO getUser(String name) {
		return dao.findByName(name);
	}
	public void modifyUser(UserVO user) {
		dao.update(user);
	}
	/*중복 유저 검사*/
	private void validateDuplicateMember(UserVO user) {
		String findUsername = dao.findByName(user.getUsername()).getUsername();
		if(findUsername == null) return; //회원 가입 가능
		else if(findUsername.equals(user.getUsername())) {
			throw new IllegalStateException("이미 존재하는 유저입니다.");
		}
	}
	
}
