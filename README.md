# 실시간 그룹채팅 앱 - 스몰톡

![12](https://github.com/user-attachments/assets/9cbe411e-3bb2-4bf3-a77f-59c33e986ec8)
## 프로젝트 주제

- 실시간으로 사용자들 간에 메시지를 주고받을 수 있는 앱 서비스입니다. 사용자는 관심사를 기반으로 다양한 채팅방에서 대화를 나누고 1:1 대화를 할 수 있으며 특정 키워드나 카테고리를 기반으로 채팅방을 검색할 수 있습니다.

## 프로젝트 목적

- 사용자들이 실시간으로 정보를 교환하고 소통할 수 있는 플랫폼을 만들었습니다.
- 어떤 기기에서나 쉽게 접근하고 사용할 수 있도록 웹과 모바일 환경 모두를 지원합니다.
- WebSocket에서 동작하는 STOMP를 기반으로하여 서버-클라이언트 간 실시간 메시지 전송 및 수신을 구현했습니다.

## 프로젝트 유형

팀 프로젝트

## 개발 인원 / 개발 기간

개발 인원 - 2명(강민준, 박경준)

프론트엔드 - 강민준

백엔드 - 박경준

개발 기간 : 2024.08.21 ~ 2024.10.04

## 사용 기술


<img src="https://github.com/user-attachments/assets/4d1752a4-ccca-49b5-9cc6-9d5661cbcd0f" width="1000" height="500"/>

아키텍쳐

<img src="https://github.com/user-attachments/assets/59c916a1-c941-4cbf-81a6-0f702b7a7e48" width="1000" height="500"/>

STOMP 통신 구조

## 핵심 기능

---

**회원가입&로그인**

- 앱을 사용하기 위해 사용자는 회원가입을 통해 계정을 생성하고 로그인을 진행할 수 있습니다.
- 유저 정보는 DB에 저장되어 닉네임을 통한 채팅앱 이용이 가능합니다.
<img src="https://github.com/user-attachments/assets/3b32cb3a-eb1f-408c-9cad-2063c468d9ae" width="200" height="400"/>
<img src="https://github.com/user-attachments/assets/af559803-2d32-4d92-9c79-dc5d5e69f26f" width="200" height="400"/>

---

**실시간 채팅**

- 여러 채팅방을 생성하고 참여할 수 있으며 각 채팅방은 특정 카테고리에 맞게 설정할 수 있습니다.
- 원하는 채팅방에 들어가 실시간 그룹채팅을 진행할 수 있습니다.
- 채팅 내용은 각 채팅방 DB에 저장되어 이후 들어오더라도 기존 채팅 내역을 확인할 수 있습니다.
<img src="https://github.com/user-attachments/assets/3d55f686-316e-47d3-8592-62bd96aad19b" width="200" height="400"/>
<img src="https://github.com/user-attachments/assets/6fd4c5b0-6938-41ff-aa2b-a07654234264" width="200" height="400"/>
---

---
**친구와 1:1 채팅**

- 사용자는 친구 추가 기능을 통해 다른 사용자와 친구를 맺고 1:1 채팅 기능을 이용할 수 있습니다.
    
    <img src="https://github.com/user-attachments/assets/899959f0-d309-4b8b-8f47-7937b56ae5ad" width="200" height="400"/>
        <img src="https://github.com/user-attachments/assets/489b566c-8874-402c-93aa-76991e0cf547" width="200" height="400"/>

    <img src="https://github.com/user-attachments/assets/5393037d-fc09-41a1-90da-0b4506ddf22e" width="200" height="400"/>

    

---

**내 채팅 기록 확인**

- 자신이 만든 채팅방과 들어갔던 채팅방의 기록을 확인할 수 있습니다.

<img src="https://github.com/user-attachments/assets/abc4ac3e-562c-4b35-bc2b-4c73eacfc473" width="200" height="400"/>
<img src="https://github.com/user-attachments/assets/afbbf4bf-a8a4-4d6c-8a89-6f233e3b6977" width="200" height="400"/>
