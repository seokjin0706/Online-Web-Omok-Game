package com.seokjin.omok.domain;

public class UserVO {
	private String username;
	private String password;
	private String email;
	private int win;
	private int loss;
	private double win_loss_ratio;
	private String message;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public double getWin_loss_ratio() {
		return win_loss_ratio;
	}
	public void setWin_loss_ratio(double win_loss_ratio) {
		this.win_loss_ratio = win_loss_ratio;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
