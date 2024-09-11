package com.HotelManagementSystem.models;

public class Room {
	private String roomid;
	private String capacity;
	private int fare;
	private String status;
	public Room() {}
	public Room(String id,String cap,int far , String st) {
		this.capacity = cap;
		this.fare = far;
		this.roomid = id;
		this.status = st;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String string) {
		this.roomid = string;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int d) {
		this.fare = d;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
