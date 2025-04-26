package com.Test.Test.services;


import com.Test.Test.models.Room;
import com.Test.Test.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(String id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElse(null);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteById(String id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<String> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public List<Room> search(String keyword, String searchType) {
        if (keyword == null || keyword.isEmpty()) {
            return findAll();
        }
        
        switch (searchType) {
            case "roomId":
                return roomRepository.findByRoomIdContaining(keyword);
            case "tenantName":
                return roomRepository.findByTenantNameContaining(keyword);
            case "phoneNumber":
                return roomRepository.findByPhoneNumberContaining(keyword);
            default:
                return findAll();
        }
    }

    @Override
    public String generateNextRoomId() {
        List<Room> rooms = roomRepository.findAll();
        
        if (rooms.isEmpty()) {
            return "PT-001";
        }
        
        int maxNumber = 0;
        for (Room room : rooms) {
            String roomId = room.getRoomId();
            if (roomId.startsWith("PT-")) {
                try {
                    int number = Integer.parseInt(roomId.substring(3));
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                } catch (NumberFormatException ignored) {
                    // If format is invalid, ignore
                }
            }
        }
        
        // Increment by 1 and format with leading zeros
        maxNumber++;
        return String.format("PT-%03d", maxNumber);
    }
}
