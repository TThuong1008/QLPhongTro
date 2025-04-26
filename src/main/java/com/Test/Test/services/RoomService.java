package com.Test.Test.services;


import com.Test.Test.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(String id);
    void save(Room room);
    void deleteById(String id);
    void deleteAll(List<String> ids);
    List<Room> search(String keyword, String searchType);
    String generateNextRoomId();
}