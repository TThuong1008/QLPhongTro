package com.Test.Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Test.Test.models.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByRoomIdContaining(String roomId);
    List<Room> findByTenantNameContaining(String tenantName);
    List<Room> findByPhoneNumberContaining(String phoneNumber);
}