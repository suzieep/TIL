# Header-Cache

## Why Cache?
- 캐시 덕분에 캐시 가능 시간동안 네트워크를 사용하지 않아도 된다.
- 비싼 네트워크 사용량을 줄일 수 있다.
- 브라우저 로딩 속도가 매우 빠르다.
- 빠른 사용자 경험
<img width="1121" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/c861c7e5-22fb-4efa-8069-0d7943c5b890">
<img width="964" alt="image" src="https://github.com/suzieep/TIL/assets/61377122/d24b4f64-010d-478e-8579-b25340074a9b">

## Proxy Cache

### Why Proxy Cache?
![image](https://github.com/suzieep/TIL/assets/61377122/bf9d0119-0403-4266-acef-9999344be6fe)
![image](https://github.com/suzieep/TIL/assets/61377122/7094d3f3-46ab-4ddd-9438-5e8e81618621) 

### Cache-Control : 기타
- Cache-Control: public
    - 응답이 public 캐시에 저장되어도 됨
- Cache-Control: private
    - 응답이 해당 사용자만을 위한 것임, private 캐시에 저장해야 함(기본값)
- Cache-Control: s-maxage
    - 프록시 캐시에만 적용되는 max-age
- Age: 60
    - Origin 서버 응답 후 Proxy Cache 내에 머문 시간(초)

## 캐시 무효화

### Cache-Control : 확실한 캐시 
- Cache-Control: no-cache
    - 데이터는 캐시해도 되지만, 항상 origin 서버에 검증하고 사용(이름에 주의!)
    ![image](https://github.com/suzieep/TIL/assets/61377122/2ab70c32-4cf5-4c27-83f3-abb92ce06d29)
    ![image](https://github.com/suzieep/TIL/assets/61377122/b00bd789-16db-46d6-8839-26e12c9083e6)
- Cache-Control: no-store
    - 데이터에 민감한 정보가 있으므로 저장하면 안됨(메모리에서 사용하고 최대한 빨리 삭제)
- Cache-Control: must-revalidate
    - 캐시 만료후 최초 조회시 origin 서버에 검증해야함
    - 원 서버 접근 실패시 반드시 오류가 발생해야함 - 504(Gateway Timeout)
    - must-revalidate는 캐시 유효 시간이라면 캐시를 사용함
    ![image](https://github.com/suzieep/TIL/assets/61377122/4c30014e-fcfe-4948-bab2-93746ae216e8)

- Pragma: no-cache
    - HTTP 1.0 하위 호환

