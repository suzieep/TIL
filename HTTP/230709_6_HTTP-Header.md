# HTTP Header
- HTTP 전송에 필요한 모든 부가정보
- 필요시 임의의 헤더 추가 가능
- 과거 RFC2616 -> 현재 RFC723x

## RFC723x
- Entity -> Representation
- Representation = Representation Metadata + Representation Data

## HTTP Body
- Message body = payload
<img width="697" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/1de43d25-d887-4fbe-887c-3dffaff5121e">

## Representation
- Content-Type: 표현 데이터의 형식
    - 미디어 타입, 문자 인코딩
    - ex) 
        - text/html; charset=utf-8
        - application/json
        - image/png
- Content-Encoding: 표현 데이터의 압축 방식
    - 표현 데이터를 압축하기 위해 사용
    - 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
    - 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
    - ex)
        - gzip
        - deflate
        - identity
- Content-Language: 표현 데이터의 자연 언어
    - ex)
        - ko
        - en
        - en-US
- Content-Length: 표현 데이터의 길이
    - 바이트 단위
    - Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안됨

## Content negotiation
- 클라이언트가 선호하는 표현 요청(request에만 사용)
- Accept: 클라이언트가 선호하는 미디어 타입 전달
- Accept-Charset: 클라이언트가 선호하는 문자 인코딩
- Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
- Accept-Language: 클라이언트가 선호하는 자연 언어
<img width="942" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/622e81ae-9a36-41b6-9290-a93923c7a945">

## Quality Values(q)
### 0~1, 클수록 높은 우선순위
- 생략하면 1
- `Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7`
    1. ko-KR;q=1 (q생략)
    2. ko;q=0.9
    3. en-US;q=0.8
    4. en;q=0.7
<img width="936" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/80b6610b-c022-4331-be06-156d093c4d58">

### 구체적인 것이 우선한다
- `Accept: text/*, text/plain, text/plain;format=flowed, */*`
    1. text/plain;format=flowed
    2. text/plain
    3. text/*
    4. */*

### 구체적인 것을 기준으로 미디어 타입을 맞춘다
- `Accept: text/*;q=0.3, text/html;q=0.7, text/html;level=1, text/html;level=2;q=0.4, */*;q=0.5`
<img width="331" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/4fd01808-e73b-4bb5-b597-53678c9a79cb">

## 전송 방식
- Content-Length : 단순 전송
<img width="1134" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/ec9be4db-c24d-4717-b520-f4b824576027">

- Content-Encoding : 압축 전송
<img width="1035" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/7c8c287a-b8d9-4c6b-9bb0-0b7f51bb006f">

- Transfer-Encoding : 분할 전송
<img width="1126" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/844ee621-9024-4f9a-a773-4447f6735822">

- Range, Content-Range : 범위 전송
<img width="1043" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/ba19d722-341f-4f4b-9b1b-d0996a72e9c2">

## 일반 정보
- From: 유저 에이전트의 이메일 정보
    - 일반적으로 잘 사용되지 않음
    - 검색 엔진 같은 곳에서, 주로 사용
    - 요청에서 사용
- Referer: 이전 웹 페이지 주소
    - 현재 요청된 페이지의 이전 웹 페이지 주소
    - A -> B로 이동하는 경우 B를 요청할 때 Referer: A 를 포함해서 요청
    - Referer를 사용해서 유입 경로 분석 가능
    - 요청에서 사용
    - 참고: referer는 단어 referrer의 오타
- User-Agent: 유저 에이전트 애플리케이션 정보
- Server: 요청을 처리하는 ORIGIN 서버의 소프트웨어 정보
    - Server: Apache/2.2.22 (Debian)
    - server: nginx
    - 응답에서 사용
- Date: 메시지가 생성된 날짜와 시간
    - Date: Tue, 15 Nov 1994 08:12:31 GMT
    - 응답에서 사용

## 특별한 정보
- Host: 요청한 호스트 정보(도메인)
    - 요청에서 사용
    - 필수
    - 하나의 서버가 여러 도메인을 처리해야 할 때
    - 하나의 IP 주소에 여러 도메인이 적용되어 있을 때
    ```http
    GET /search?q=hello&hl=ko HTTP/1.1
    Host: www.google.com
    ```
- Location: 페이지 리다이렉션
    - 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동(리다이렉트)
    - 응답코드 3xx에서 설명
    - 201 (Created): Location 값은 요청에 의해 생성된 리소스 URI
    - 3xx (Redirection): Location 값은 요청을 자동으로 리디렉션하기 위한 대상 리소스를 가리킴
- Allow: 허용 가능한 HTTP 메서드
    - 405 (Method Not Allowed) 에서 응답에 포함해야함
    - Allow: GET, HEAD, PUT
- Retry-After: 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
    - 503 (Service Unavailable): 서비스가 언제까지 불능인지 알려줄 수 있음
    - Retry-After: Fri, 31 Dec 1999 23:59:59 GMT (날짜 표기)
    - Retry-After: 120 (초단위 표기)

## 인증
- Authorization: 클라이언트 인증 정보를 서버에 전달
- WWW-Authenticate: 리소스 접근시 필요한 인증 방법 정의
    - 리소스 접근시 필요한 인증 방법 정의
    - 401 Unauthorized 응답과 함께 사용
    - `WWW-Authenticate: Newauth realm="apps", type=1, title="Login to \"apps\"",Basic realm="simple"`

