# Internet Network
![image](https://github.com/suzieep/TIL/assets/61377122/61a65457-daff-412c-8017-6e66556a1a0a)


## IP : Internet Protocol 인터넷 프로토콜
- 지정한 IP Address에 통신 단위인 Packet으로 데이터 전달
- IP 한계
    - 비연결성 : 패킷 받을 대상이 없거나 서비스 불능 상태여도 패킷 전송
    - 프로그램 구분 : 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상이라면
    - 비신뢰성 : 중간에 패킷이 사라지거나 순서대로 오지 않을때 보장이 되지 않음
    ![image](https://github.com/suzieep/TIL/assets/61377122/b2b9332d-64c6-4c52-bcba-b0e09acd1c37)


## TCP : Transmission Control Portocol 전송 제어 프로토콜
- 데이터 전달 보증 : 데이터 잘 받았는지 알려줌
- 순서 보장 : 중간에 잘못된 순서로 오면, 클라이언트에 잘못된 부분부터 다시 요청
- 연결지향 : TCP 3 way handshake
![image](https://github.com/suzieep/TIL/assets/61377122/e708c8fa-40a9-4095-97ed-c45060fcd491)
![image](https://github.com/suzieep/TIL/assets/61377122/ff7d378a-7ee2-4d11-ad55-82dc16190b47)
![image](https://github.com/suzieep/TIL/assets/61377122/8cf59887-0068-4767-b215-1bbcf0534b9d)




### UDP : User Datagram Protocol 사용자 데이터그램 프로토콜
- 하얀 도화지에 비유(기능이 거의 없음)
- 연결지향X, 데이터 전달 보증X, 순서 보장X -> 단순하고 빠름
- IP + PORT + 체크섬

### PORT
- 같은 IP 내에서 프로세스 구분

- 0 ~ 65535 할당 가능
- 0 ~ 1023: 잘 알려진 포트, 사용하지 않는 것이 좋음
    - FTP - 20, 21
    - TELNET - 23
    - HTTP - 80
    - HTTPS - 443

### DNS : Domain Name System 도메인 네임 시스템
- 전화번호부 같은 서버 제공
![image](https://github.com/suzieep/TIL/assets/61377122/7d147ea7-6610-480a-934a-0af60bf6f721)