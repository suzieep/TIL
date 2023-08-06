# SpringDB2/Section1) 데이터 접근 기술 - 시작

## Profile

spring.profiles.active = local -> 로컬이라는 프로필로 동작할게, local만 Bean 등록해!(없으면 default)

```java
@Profile("local")
```

로컬, 운영 환경, 테스트 실행 등등 다양한 환경에 따라서 다른 설정을 할 때 사용하는 정보

interface를 테스트 하자! -> 향후 다른 구현체로 변경되었을 때 해당 구현체가 잘 동작하는지 같은 테스트로 편리하게 검증 가능

item... 알아보기
