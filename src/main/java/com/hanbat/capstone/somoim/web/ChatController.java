package com.hanbat.capstone.somoim.web;

import com.hanbat.capstone.somoim.domain.ChatMessage;
import com.hanbat.capstone.somoim.domain.ChatRoom;
import com.hanbat.capstone.somoim.dto.ChatRoomRequest;
import com.hanbat.capstone.somoim.repository.ChatMessageRepository;
import com.hanbat.capstone.somoim.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations template;
    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomRepository chatRoomRepository;

    //채팅방 생성
    @PostMapping("/chatroom")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom(request.getName(), request.getCategory());  // 카테고리도 함께 저장
        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        return ResponseEntity.ok(savedRoom);
    }
    //모든 채팅방 조회
    @GetMapping("/chatrooms")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return ResponseEntity.ok(chatRooms);
    }

    // 특정 채팅방의 메시지 리스트 반환
    @GetMapping("/chat/{roomId}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(roomId);
        return ResponseEntity.ok().body(messages);
    }

    //특정 카테고리 리스트 반환
    @GetMapping("/chatrooms/category/{category}")
    public ResponseEntity<List<ChatRoom>> getChatRoomsByCategory(@PathVariable String category) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByCategory(category);
        return ResponseEntity.ok().body(chatRooms);
    }

    // 메시지 송신 및 수신, roomId를 경로에서 받아옴
    @MessageMapping("/chat/{roomId}")
    public void receiveMessage(@DestinationVariable Long roomId, @RequestBody ChatMessage chatMessage) {

        // 채팅방 ID를 설정
        chatMessage.setRoomId(roomId);

        // 메시지를 DB에 저장
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // 저장된 메시지를 해당 채팅방 구독자들에게 전송
        String destination = "/sub/chatroom/" + roomId;
        template.convertAndSend(destination, savedMessage);


    }
}


