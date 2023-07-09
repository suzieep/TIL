# HTTP-Status-Code

## 1xx (Informational): 요청이 수신되어 처리중(거의 사용X)
## 2xx (Successful): 요청 정상 처리
- 200 OK : 요청 성공
- 201 Created : 요청 성공해서 새로운 리소스가 생성됨(생성된 리소스는 응답의 Location 헤더 필드로 식별 가능)
- 202 Accepted : 요청이 접수되었으나 처리가 완료되지 않았음
- 204 No Content : 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음
## 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요함
> 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동
- 300 Multiple Choices : 안 씀

영구 리다이렉션 - 특정 리소스의 URI가 영구적으로 이동
- 301 Moved Permanently : 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음(MAY)
- 308 Permanent Redirect : 리다이렉트시 요청 메서드와 본문 유지(처음 POST를 보내면 리다이렉트도 POST 유지)

일시 리다이렉션 - 일시적인 변경
- 302 Found : GET으로 변할 수 있음
- 303 See Other : 메서드가 GET으로 변경 
- 307 Temporary Redirect : 메서드,본문이 변하면 안됨 
> PRG: Post/Redirect/Get
> POST로 주문하고 새로고침하면 중복 -> GET으로 결과 화면 redirect

- 304 Not Modified
    - 캐시를 목적으로 사용
    - 클라이언트에게 리소스가 수정되지 않았음을 알려줌(클라이언트는 로컬에 저장된 캐시로 리다이렉트)
    - 응답에 바디 포함 불가(로컬 캐시 사용 위해)
    - 조건부 GET,HEAD 요청시 사용????????????
## 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 400 Bad Request : 클라이언트가 잘못된 요청함(재시도 실패)
- 401 Unauthorized : 클라이언트가 해당 리소스에 대한 "인증"이 필요함
    - 응답에 WWW-Authenticate Header와 함께 인증 방법을 설명
    > Authentication vs Authorization
    > Authentication 인증 : 본인이 누구인지 확인(로그인)
    > Authorization 인가 : 권한부여(Admin 등의 등급) 인증->인가
- 403 Forbidden : 서버가 요청을 이해했지만 승인을 거부함(접근권한)
- 404 Not Found : 요청 리소스 찾을 수 없음(권한 없을 때 권한때문인 것도 숨기고 싶을 때 띄우기도 함)

## 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함(재시도 성공 가능성O)
- 500 Internal Server Error : 서버 문제로 오류 발생, 애매하면 500 띄움
- 503 Service Unavailable : 서비스 이용 불가(과부하, 예정 작업 등으로 잠시 요청 처리 불가)
    - Retry-After Header로 얼마뒤에 복구되는지 보낼 수 있음

### +
Location Header 써본적 없는데 잘 활용해야겠다