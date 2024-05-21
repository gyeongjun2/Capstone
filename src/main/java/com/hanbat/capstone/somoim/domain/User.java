package com.hanbat.capstone.somoim.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    public User() {
    }

    //PK 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //속성 설정
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    //private String email; 넣을수도 있고 안넣을수도 잇고

    //private String role;

}
