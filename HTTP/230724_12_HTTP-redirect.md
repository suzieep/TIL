# HTTP Redirect/Foward

## HTTP Redirect
- 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요함
- 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동
![image](https://github.com/suzieep/TIL/assets/61377122/f261a2c8-6bc9-47f5-9b89-bf16d6b086d9)

### 영구 리다이렉션 
-> 특정 리소스의 URI가 영구적으로 이동
- 301 Moved Permanently
    - 리다이렉트시 요청 메서드가 GET으로 변함
    - 본문이 제거될 수 있음
    - 웹 사이트가 재편성 되었을 때
- 308 Permanent Redirect
    - 리다이렉트시 요청 메서드와 본문 유지
    - 링크/동작을 지닌 웹 사이트의 재편성에 사용

### 일시 리다이렉션 
-> 일시적인 변경
- 302 Found : GET으로 변할 수 있음
- 303 See Other : 메서드가 GET으로 변경 
- 307 Temporary Redirect : 메서드,본문이 변하면 안됨 
> PRG: Post/Redirect/Get
> POST로 주문하고 새로고침하면 중복 -> GET으로 결과 화면 redirect

### 특수 리다이렉션 
- 300 Multiple Choices
- 304 Not Modified
    - 캐시를 목적으로 사용
    - 클라이언트에게 리소스가 수정되지 않았음을 알려줌(클라이언트는 로컬에 저장된 캐시로 리다이렉트)
    - 응답에 바디 포함 불가(로컬 캐시 사용 위해)
    - 조건부 GET,HEAD 요청시 사용

## Foward
- 클라이언트가 한 번 더 요청을 보내도록 하는 리다이렉트와 달리, 서버 내부에서 일어나는 호출로 서버가 모든 동작 처리
![image](https://github.com/suzieep/TIL/assets/61377122/e77a642a-1b76-4470-8f3d-9109dc530e1f)


## Redirect vs Foward
리다이렉트 : 클라이언트 요청에 의해 서버의 DB 변화가 생기는 작업에 사용 ex. 회원가입
- URL 변화 O 
- 객체 재사용 X
-> 처음 보냈던 최초의 요청 정보 더이상 유효하지 않음 

포워드 : 특정 URL에 대해 외부에 공개되지 말아야 하는 부분 가리거나 조회하기 위해 사용(시스템에 변화가 생기지 않는 단순조회) ex. 공개되면 안되는 데이터 감쌀 때
- URL 변화 X
- 객체 재사용 O



