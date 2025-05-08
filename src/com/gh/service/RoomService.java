package com.gh.service;

import java.time.LocalDate;
import java.util.List;

import com.gh.vo.room.Room;

public interface RoomService {
	void addRoom(Room room);

	void removeRoom(int roomNum);

	List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut);

	void updateRoomType(int roomNum, String newType);
}
