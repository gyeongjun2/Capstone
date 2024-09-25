package com.hanbat.capstone.somoim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto {
    private Long id;
    private String name;
    private String category;
    private String creatorNickname; // 생성자의 닉네임만 전달

    public ChatRoomDto(Long id, String name, String category, String creatorNickname) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.creatorNickname = creatorNickname;
    }
}
