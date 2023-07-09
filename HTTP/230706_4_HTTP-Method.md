# HTTP-Method

# API URI
- 리소스 식별 - URI
- 행위 - Method
    - **동사**로된 컨트롤 URI 하기도함 (/start-delivery)

# Method
주요 메서드
- GET: 리소스 조회
    - 데이터 넣어도 되는데 지양
- POST: 요청 데이터 처리
    - 메시지 바디를 통해 서버로 요청 데이터 전달
    - 주로 전달된 데이터로 신규 리소스 등록, **프로세스 처리에 사용**
    - 조회인데, JSON데이터가 필요하면 POST 사용함
    - 서버가 Collection에 리소스 관리
- PUT: 리소스를 **완전히** 대체(덮어쓰기), **해당 리소스가 없으면 생성**
    - **클라이언트가 리소스를 식별(/posts/2)** -> 클라이언트가 URI 지정, Store에 리소스 저장(ex. files/star.jpg)
- PATCH: 리소스 부분 변경
- DELETE: 리소스 삭제

기타 메서드
- HEAD: GET과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환
- OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명(주로 CORS에서 사용)
- CONNECT: 대상 리소스로 식별되는 서버에 대한 터널을 설정
- TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

## HTTP Method 속성
- 안전(Safe Methods) : 호출해도 리소스를 변경하지 않는다.
- 멱등(Idempotent Methods) : 한 번 호출하든 두 번 호출하든 100번 호출하든 결과가 똑같다.
    - GET, PUT, DELETE 해당
    - 자동 복구 매커니즘에 활용 가능(서버가 정상 응답 못했을 때 클라가 다시 요청해도 되나?)
- 캐시가능(Cacheable Methods) : 응답 결과 리소스를 캐시해서 사용
    - **GET, HEAD**, POST, PATCH 해당

HTML FORM - GET, POST만 지원
