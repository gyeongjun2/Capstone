package com.hanbat.capstone.somoim.repository;

import com.hanbat.capstone.somoim.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomId(Long roomId);
}
