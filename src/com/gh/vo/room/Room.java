package com.gh.vo.room;

import java.time.LocalDate;

public class Room {
	private int roomNum;
	private int roomSize;
	private int capacity;
	private boolean status = true;
	private String type;
	private int price;
	private LocalDate maintenanceStart;
	private LocalDate maintenanceEnd;

	public Room() {

	}

	public Room(int roomNum, int roomSize, int capacity, boolean status, String type, int price) {
		this.roomNum = roomNum;
		this.roomSize = roomSize;
		this.capacity = capacity;
		this.status = status;
		this.type = type;
		this.price = price;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public LocalDate getMaintenanceStart() {
		return maintenanceStart;
	}
	
	public void setMaintenanceStart(LocalDate maintenanceStart) {
		this.maintenanceStart = maintenanceStart;
	}
	
	public LocalDate getMaintenanceEnd() {
		return maintenanceEnd;
	}
	
	public void setMaintenanceEnd(LocalDate maintenanceEnd) {
		this.maintenanceEnd = maintenanceEnd;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomSize=" + roomSize + ", capacity=" + capacity + ", status=" + status
				+ ", type=" + type + ", price=" + price + "]";
	}
	

}
