package com.hanbat.capstone.somoim.web;


import com.hanbat.capstone.somoim.domain.User;
import com.hanbat.capstone.somoim.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()).isPresent()){ //받아온 유저데이터의 아이디가 있다면
            return ResponseEntity.badRequest()
                    .body("이 아이디는 사용중입니다.");
        }
        //아이디가 없는 아이디라면 회원가입 시켜주기
        User registUser = userService.registerUser(user);
        return ResponseEntity.ok(registUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser, HttpServletRequest request) {
        Optional<User> optionalUser = userService.findByUsername(loginUser.getUsername());

        if (optionalUser.isPresent() && userService.checkPassword(loginUser.getPassword(), optionalUser.get().getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", optionalUser.get());
            return ResponseEntity.ok().body(optionalUser.get().getNickname());
        } else {
            return ResponseEntity.status(401).body("아이디나 비밀번호가 틀렸습니다.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }


}
