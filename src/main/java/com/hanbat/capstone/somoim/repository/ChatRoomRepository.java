//package com.hanbat.capstone.somoim.repository;
//
//import com.hanbat.capstone.somoim.domain.ChatRoom;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
//    List<ChatRoom> findByNameContaining(String name);
//}
package com.hanbat.capstone.somoim.repository;

import com.hanbat.capstone.somoim.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByCategory(String category);
}