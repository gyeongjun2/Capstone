package com.hanbat.capstone.somoim.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private String name;
    private String message;

    // 메시지가 생성될 때 자동으로 현재 시간이 기록됨
    private ZonedDateTime timestamp;

    // 생성자 추가
    public ChatMessage(Long roomId, String name, String message) {
        this.roomId = roomId;
        this.name = name;
        this.message = message;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Seoul")); // 한국 시간대로 시간 설정
    }

}


