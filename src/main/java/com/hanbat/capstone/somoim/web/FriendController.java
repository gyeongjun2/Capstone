package com.hanbat.capstone.somoim.web;

import com.hanbat.capstone.somoim.dto.*;
import com.hanbat.capstone.somoim.domain.User;
import com.hanbat.capstone.somoim.service.FriendService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/bro")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestBody FriendRequestDto friendRequestDto) {
        // JSON에서 받은 데이터를 사용하여 친구 요청 처리
        friendService.sendFriendRequest(friendRequestDto.getSenderNickname(), friendRequestDto.getReceiverNickname());
        return ResponseEntity.ok("친구 요청을 보냈습니다.");
    }

    // 받은 친구 요청 목록
    @GetMapping("/request-list")
    public ResponseEntity<List<FriendRequestResponseDto>> getFriendRequests(HttpServletRequest request) {
        String currentUserNickname = (String) request.getSession().getAttribute("nickname");

        if (currentUserNickname == null) {
            return ResponseEntity.status(401).body(null); // 로그인되어 있지 않은 경우
        }

        // 닉네임으로 친구 요청을 조회하고 DTO로 변환
        List<FriendRequestResponseDto> friendRequests = friendService.getFriendRequestDtosByNickname(currentUserNickname);

        return ResponseEntity.ok(friendRequests);
    }

    // 친구 요청 수락
    @PostMapping("/request-accept")
    public ResponseEntity<?> acceptFriendRequest(@RequestBody AcceptFriendRequestDto acceptRequestDto) {
        // JSON 형식으로 전달된 requestId를 사용
        friendService.acceptFriendRequest(acceptRequestDto.getRequestId());
        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }

    // 친구 목록 조회
    @PostMapping("/bro-list")
    public ResponseEntity<List<FlistDto>> getFriends(@RequestBody NicknameDto nicknameDto) {
        List<User> friends = friendService.getFriends(nicknameDto.getNickname());

        // User 객체를 UserDto로 변환
        List<FlistDto> friendsDto = friends.stream()
                .map(friend -> new FlistDto(friend.getId(), friend.getUsername(), friend.getNickname()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(friendsDto);
    }
}

