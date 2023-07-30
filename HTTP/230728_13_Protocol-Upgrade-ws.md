# Protocol Upgrade
HTTP 프로토콜 업그레이드 메커니즘은 기존 HTTP 1.1 연결을 HTTP 2.0과 같은 최신 버전의 프로토콜로 업그레이드하는 데 사용됩니다.

프로토콜 업그레이드의 가장 큰 문제는 클라이언트와 서버가 모두 새 버전의 프로토콜을 지원한다는 보장이 없다는 점입니다.이로 인해 호환성 문제와 통신 오류가 발생할 수 있습니다.

호환성 문제 - 사전 협상
버전 최신화 - 프로토콜 협상
최신버전 지원 x - 하위 호환성 



HTTP /1.1 프로토콜은 Upgrade 헤더 필드를 사용하여 이미 설정된 연결을 다른 프로토콜로 업그레이드하는 데 사용할 수 있는 특수 메커니즘을 제공합니다 .\
hop-by-hop 헤더이기 때문에 헤더 필드 Upgrade에도 나열되어야 합니다 Connection. 즉, 업그레이드를 포함하는 일반적인 요청은 다음과 같습니다.
```
GET /index.html HTTP/1.1
Host: www.example.com
Connection: upgrade
Upgrade: example/1, foo/2
```

## WebSocket
```
Connection: Upgrade
Upgrade: websocket
```

```
GET /chat HTTP/1.1
Host: server.example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: x3JJHMbDL1EzLkh9GBhXDw== //핸드쉐이크 응답 검증 
Sec-WebSocket-Protocol: chat, superchat
Sec-WebSocket-Version: 13
Origin: http://example.com
```

```
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: HSmrc0sMlYUkAGmm5OPpG2HaGWk=
Sec-WebSocket-Protocol: chat
```

![image](https://github.com/suzieep/TIL/assets/61377122/72b0e38a-dc04-4fc7-949c-76afe693d1e2)


웹소켓의 탄생
HTTP를 이용한 실시간 통신의 문제를 해결하기 위해 HTML5부터 웹소켓이 등장했습니다. 웹소켓은 실시간 양방향 통신을 지원하며 한번 연결이 수립되면 클라이언트와 서버 모두 자유롭게 데이터를 보낼 수 있습니다. 이는 채팅과 같은 연속적인 통신에 대해 계속 유사한 통신을 반복하지 않게 해주어 통신의 효율성도 개선하였습니다.


웹소켓 프로토콜
웹소켓은 HTTP와 같은 OSI 모델의 7계층에 위치하는 프로토콜이며, 4계층의 TCP에 의존합니다.


HTTP를 이용해서 연결을 수립하며 연결 된 이후에도 연결을 할 때 사용했던 포트인 80과 443포트를 이용합니다. 연결 수립은 핸드쉐이크를 통해 이루어지며 핸드쉐이크시 HTTP를 이용합니다.


![image](https://github.com/suzieep/TIL/assets/61377122/8982c588-ae12-4060-b96a-405f6efc7ca5)
위 이미지의 빨간 색 박스에 해당하는 Opening Handshake
위 이미지의 노란 색 박스에 해당하는 Data transfer
위 이미지의 보라 색 박스에 해당하는 Closing Handshake

### Opening Handshake

```
GET /chat HTTP/1.1
Host: server.example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: x3JJHMbDL1EzLkh9GBhXDw==
Sec-WebSocket-Protocol: chat, superchat
Sec-WebSocket-Version: 13
Origin: http://example.com
```
GET /chat HTTP/1.1
웹소켓의 통신 요청에서,
HTTP 버전은 1.1 이상이어야하고 GET 메서드를 사용해야햔다.

 

Upgrade
프로토콜을 전환하기 위해 사용하는 헤더.
웹소켓 요청시에는 반스에 websocket 이라는 값을 가지며,
이 값이 없거나 다른 값이면 cross-protocol attack 이라고 간주하여 웹 소켓 접속을 중지시킨다.

 

Connection
현재의 전송이 완료된 후 네트워크 접속을 유지할 것인가에 대한 정보.
웹 소켓 요청 시에는 반드시 Upgrade 라는 값을 가진다.
Upgrade 와 마찬가지로 이 값이 없거나 다른 값이면 웹소켓 접속을 중지시킨다.

 

Sec-WebSocket-Key
유효한 요청인지 확인하기 위해 사용하는 키 값

 

Sec-WebSocket-Protocol
사용하고자 하는 하나 이상의 웹 소켓 프로토콜 지정.
필요한 경우에만 사용

 

Sec-WebSocket-Version
클라이언트가 사용하고자 하는 웹소켓 프로토콜 버전.
현재 최신 버전 13

 

Origin
CORS 정책으로 만들어진 헤더.
Cross-Site Websocket Hijacking과 같은 공격을 피하기 위함.

Sec-WebSocket-Accept: “Sec-WebSocket-Key” 헤더를 통해 받은 값에 특정 값을 붙인 후, SHA-1로 해싱하고 base64로 인코딩한 값

```
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: HSmrc0sMlYUkAGmm5OPpG2HaGWk=
Sec-WebSocket-Protocol: chat
```
HTTP/1.1 101 Switching Protocols
101은 HTTP에서 WS로 프로토콜 전환이 승인 되었다는 응답코드이다.

 

Sec-WebSocket-Accept
요청 헤더의 Sec-WebSocket-Key에 유니크 아이디를 더해서 SHA-1로 해싱한 후 base64로 인코딩한 결과이다.
웹 소켓 연결이 개시되었음을 알린다.

### 웹소켓 서버에서 보안 이슈
1. CORS

이번 프로젝트에서는 메신저 기능을 위해 브로드캐스팅 기능을 지원하는 socket.io 라이브러리를 활용했다. 백엔드 파트에 API 요청을 처리하는 메인 서버와 메시지를 처리하는 웹소켓 서버를 따로 두었고, 각각 별도의 포트를 할당했다. 되돌아보면 이때 처음부터 각 서버가 사용하는 모든 소스코드를 분리하지 않고 포트만 분리한 것이 실수였다. 로컬 호스트 환경에서 개발을 할 때와 도메인을 붙여 배포할 때의 통신 방식은 완전히 달라져야 하기 때문이다.
클라이언트가 접속하는 웹서버와 요청을 처리하는 백엔드 서버에 도메인과 인증서를 추가하고 HTTPS 통신을 시작하자, CORS(cross-origin-resource-sharing) 오류 메시지와 함께 일부 요청이 도달하지 못하는 문제가 발생했다. 의문스러운 상황이었다. 기본적으로 CORS 오류는 브라우저의 보안 원칙인 ‘동일 출처 정책’에 의해 발생한다. 이는 서로 다른 사이트 간에 리소스를 주고받는 것이 잠재적으로 위험할 수 있으니 제한하는 정책이다. 
하지만 양쪽에서 서로의 URL을 허용해두면 브라우저와 서버는 서로를 허용된 출처로 인식하고, 리소스를 공유할 수 있게 된다. 도메인을 붙인 후 서로의 도메인을 허용하도록 했는데 CORS 오류가 발생하여 매우 당황스러웠다. 
![image](https://github.com/suzieep/TIL/assets/61377122/13f6e811-a7be-4e27-abb3-aad867f0d0fa)
문제는 클라이언트와의 통신이 아닌, 메인 서버와 웹소켓 서버 간 통신에 있었다. 같은 도메인이더라도 포트가 다르면 서로 같은 출처가 아니게 되고, 이 둘 사이의 통신에서 CORS 오류가 발생한 것이다. 로컬호스트에서 개발할 때는 미처 예상하지 못했던 문제였다. 이를 해결하기 위해서는 두 서버 사이에 중개자 역할을 하는 프록시 서버를 두거나 추가적인 API를 만들어야 했다.\

2. SSL 프로토콜 에러
한편 클라이언트 웹서버와 웹소켓 서버가 웹소켓 통신을 할 때도 또 다른 보안 관련 문제가 발생했다. 클라이언트 웹서버가 HTTPS로 보호받고 있기 때문에, 웹소켓 통신에도 보안이 적용된 WSS 프로토콜을 사용해야 했다. WSS는 HTTPS와 마찬가지로 SSL/TLS에 의해 암호화된 프로토콜이다. socket.io의 경우 엔드 포인트의 HTTP URL을 받아서 폴링(polling)을 통해 연결을 수립한 후, 웹소켓 프로토콜이 지원되는지 확인하여 WS 프로토콜로 전환한다. SSL/TLS 암호화 처리가 되어있는 사이트끼리는 HTTPS 프로토콜에 의한 통신이 가능하고, 이들 사이의 웹소켓 통신 역시 암호화된다.


1. NGINX 리버스 프록시
장기적인 해결책은 클라이언트 웹서버와 메인 서버, 웹소켓 서버에 대한 요청을 모두 NGINX에서 받아, 각각의 서버로 라우팅을 시켜주는 리버스 프록시 방식이었다. 이 방식이 효율적이고 적합하다는 것에 모두 동의했지만, 팀 내에 도커와 NGINX에 익숙한 사람이 없어 장기적인 스터디가 필요하다는 결론을 내렸다. 그래서 팀원 중 한 명이 먼저 스터디를 시작하고, 데모 버전 제출 이후 보완하여 적용하기로 했다.

먼저 백엔드 레포지토리에 섞여 있는 메인 서버와 웹소켓 서버의 소스코드를 별도의 파일로 분리하여 공유되는 전역 컨텍스트를 없앤다. 이로써 메인 서버, 웹소켓 서버는 모두 독립적으로 실행된다.
도메인과 인증서는 NGINX에 연결한다.
NGINX가 받은 요청을 HTTPS용 443포트로 리다이렉트하고, 요청받은 url을 파싱하여 목적지를 확인한다. 이후 클라이언트 웹서버, 메인 서버, 웹소켓 서버 각각으로 분리해서 라우팅하도록 한다.

2) AWS 로드밸런서와 ACM 인증
임시방편으로는 EC2 인스턴스 두 개에 각각 메인 서버와 웹소켓 서버를 설치하고, 로드밸런서를 연결했다. AWS로드밸런서의 역할은 NGINX와 마찬가지로 80포트로 들어온 요청을 HTTPS 기본 포트인 443포트로 리다이렉트하는 것이었다. 빠르게 ACM 인증서를 요청하여 로드밸런서에 연결한 후, 각각의 로드밸런서에서 HTTPS로 보내준 요청을 EC2 인스턴스로 포워딩하였다. 
서버 사이를 중개해 줄 프록시 서버가 없으므로 메인 서버와 소켓 서버 간 통신도 우선 차단하고 클라이언트가 API 요청에 대한 응답을 받은 후, 다시 소켓 서버에 직접 요청을 보내는 방식으로 변경했다.
이로써 웹소켓 서버 보안 문제는 해결했지만, 이 방식은 두 가지 단점이 있다. 먼저 EC2 인스턴스를 별도로 운영함으로써 운영체제를 레벨의 컴퓨팅 자원이 두 배로 소모된다는 점에서 자원의 비효율이 발생한다. 또한 클라이언트가 API 와 웹소켓 요청을 모두 직접 보내야 해서 시간의 비효율도 발생한다. 이러한 단점 때문에 빠른 시일 내 리버스 프록시 방식으로 전환할 예정이다.

오늘 알아본 것처럼 웹소켓 프로토콜은 HTTP와는 다른 별도의 통신 프로토콜이며, 브라우저에서 제공하는 웹소켓을 통해 지속적인 연결이 유지되는 실시간 양방향 통신 방식이다. 하지만 핸드셰이크를 위해 HTTP 프로토콜에 의존하는 애플리케이션 계층의 프로토콜에 불과하며, 자체적인 암호화나 보안 장치를 지원하지 않는다. 
따라서 HTTP 통신 프로토콜들과 마찬가지로 웹소켓 프로토콜 역시 보안상의 취약점이 존재하며, 암호화 레이어를 사용하더라도 100% 안전을 보장할 수 없다. 그럼에도 SSL/TLS 암호화는 웹 표준 네트워크 보안 규칙에 해당하므로 꼭 지켜야 하며, 최소한의 안전을 보장해 주는 장치다. 
브라우저는 안전하지 않은 통신을 결코 허용하지 않는다. 특히 일시적인 연결을 맺는 HTTP 통신이나 폴링(polling) 방식과는 달리, 서버와 클라이언트 간에 지속적인 연결이 유지되는 웹소켓 통신은 보안 연결을 맺지 않으면 피해가 더 커질 수 있다. 브라우저가 보내는 CORS 경고 혹은 SSL/TLS 인증서에 대한 요구는 이러한 피해를 최소화하기 위한 조치이니, 참고하여 웹소켓 통신을 안전하게 사용하도록 하자.


https://yozm.wishket.com/magazine/detail/1911/