package com.hanbat.capstone.somoim.dto;

import com.hanbat.capstone.somoim.domain.ChatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomResponseDto {
    private Long id;
    private String name;
    private String category;
    private String creatorNickname; // 닉네임만 포함

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.category = chatRoom.getCategory();
        this.creatorNickname = chatRoom.getCreatorNickname(); // 닉네임만 응답에 포함
    }
}
