package com.hanbat.capstone.somoim.web;


import com.hanbat.capstone.somoim.domain.User;
import com.hanbat.capstone.somoim.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

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

    // 로그인 성공 시 HttpOnly 및 Secure 쿠키 설정
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser, HttpServletRequest request, HttpServletResponse response) {
        Optional<User> optionalUser = userService.findByUsername(loginUser.getUsername());

        if (optionalUser.isPresent() && userService.checkPassword(loginUser.getPassword(), optionalUser.get().getPassword())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute("user", optionalUser.get());
            session.setAttribute("nickname", optionalUser.get().getNickname());

            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true); // HTTPS를 사용하는 경우
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);

            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
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
    @GetMapping("/getNickname")
    public ResponseEntity<?> getNickname(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String nickname = (String) session.getAttribute("nickname");
            if (nickname != null) {
                return ResponseEntity.ok(Collections.singletonMap("nickname", nickname));
            }
        }
        return ResponseEntity.status(401).body("User not logged in");
    }

}
