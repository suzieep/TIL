# HTTP Redirect

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