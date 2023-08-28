# Builder Pattern

객체를 생성할 때 생성자(Constructor)만 사용할 때 발생할 수 있는 문제를 개선
팩토리 메소드 패턴이나 추상 팩토리 패턴에서는 생성해야하는 클래스에 대한 속성 값이 많을 때 아래와 같은 이슈들이 있다.

1. 클라이언트 프로그램에서 팩토리 클래스를 호출할 때 Optional한 인자가 많아지면, 타입과 순서에 대한 관리가 어려워져 에러 발생 확률이 높아진다.
2. 경우에 따라 필요 없는 파라미터들에 대해서 팩토리 클래스에 일일이 NULL 값을 넘겨줘야한다.
3. 생성해야 하는 sub class가 무거워지고 복잡해짐에 따라 팩토리 클래스 또한 복잡해진다.
4. 불필요한 생성자를 만들지 않고 객체를 만든다.
5. 데이터의 순서에 상관 없이 객체를 만들어 낸다.
6. 사용자가 봤을때 명시적이고 이해할 수 있어야 한다.
   => 점층적 생성자 패턴

- 필수 매개변수와 함께 선택 매개변수를 0개, 1개, 2개 .. 받는 형태(사용하던 생성자 오버로딩)
  점층적 생성자 패턴으로 구현하면 Optional한 인자에 따라 새로운 생성자를 만들거나, Null 값으로 채워야하는 이슈가 발생한다. =>인자가 많은 경우 타입과 순서로 발생할 수 있는 에러 가능성
  => Setter

1. 함수 호출이 인자만큼 이루어지고, 객체 호출 한번에 생성할 수 없다.
2. immutable 객체를 생성할 수 없다. (setter로 값 변경 가능)
   - 쓰레드간 공유 가능한 객체 일관성(consistency)이 일시적으로 깨질 수 있다.

1) 일관성 문제
   필수 매개변수란 객체가 초기화될때 반드시 설정되어야 하는 값이다. 하지만 개발자가 깜빡하고 setBun() 이나 setPatty() 메서드를 호출하지 않았다면 이 객체는 일관성이 무너진 상태가 된다. 즉, 객체가 유효하지 않은 것이다. 만일 다른곳에서 햄버거 인스턴스를 사용하게 된다면 런타임 예외가 발생할 수도 있다.
   이는 객체를 생성하는 부분과 값을 설정하는 부분이 물리적으로 떨어져 있어서 발생하는 문제점이다. 물론 이는 어느정도 생성자(Constructor)와 결합하여 극복은 할 수 있다. 하지만 다음에 소개할 불변성의 문제 때문에 자바 빈즈 패턴은 지양해야 한다.

2) 불변성 문제
   자바 빈즈 패턴의 Setter 메서드는 객체를 처음 생성할때 필드값을 설정하기 위해 존재하는 메서드이다. 하지만 객체를 생성했음에도 여전히 외부적으로 Setter 메소드를 노출하고 있으므로, 협업 과정에서 언제 어디서 누군가 Setter 메서드를 호출해 함부로 객체를 조작할수 있게 된다. 이것을 불변함을 보장할 수 없다고 얘기한다.
   마치 완성된 햄버거에 중간에 치즈를 교체한다고 햄버거를 막 분리하는 것과 같은 이치이다 (입맛 떨어지게)

=> Builder
필요한 객체를 직접 생성하는 대신, 먼저 필수 인자들을 생성자에 전부 전달하여 빌더 객체를 만든다.
그리고 선택 인자는 가독성이 좋은 코드로 인자를 넘길 수 있다.
setter가 없으므로 객체 일관성을 유지하여 불변 객체로 생성할 수 있다.

![image](https://github.com/suzieep/TIL/assets/61377122/3ab364d3-124c-409d-813f-0a53a1631a88)
빌더 패턴에서도 디폴트 매개변수를 구현하는 방법은 똑같다. 다만 빌더라는 객체 생성 전용 클래스를 경유하여 이용함으로써 디폴트 매개변수가 설정된 필드를 설정하는 메서드를 호출하지 않는 방식으로 마치 디폴트 매개변수를 생략하고 호출하는 효과를 간접적으로 구현할수 있게 된다.

```java
class Student {
    private int id;
    private String name;
    private String grade = "freshman"; // 디폴트 매개변수 역할
    private String phoneNumber;

    public Student(int id, String name, String grade, String phoneNumber) {
        ...
    }

    // 디폴트 매개변수를 제외한 인자들을 받는 생성자 오버로딩
    public Student(int id, String name, String phoneNumber) {
        ...
    }

```

빌더 클래스를 통해 초기화가 필수인 멤버는 빌더의 생성자로 받게 하여 필수 멤버를 설정해주어야 빌더 객체가 생성되도록 유도하고, 선택적인 멤버는 빌더의 메서드로 받도록 하면, 사용자로 하여금 필수 멤버와 선택 멤버를 구분하여 객체 생성을 유도할 수 있다.

```java
class StudentBuilder {
    // 초기화 필수 멤버
    private int id;

    // 초기화 선택적 멤버
    private String name;
    private String grade;
    private String phoneNumber;

    // 필수 멤버는 빌더의 생성자를 통해 설정
    public StudentBuilder(int id) {
        this.id = id;
    }

    // 나머지 선택 멤버는 메서드로 설정
    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder grade(String grade) {
        this.grade = grade;
        return this;
    }

    public StudentBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Student build() {
        return new Student(id, name, grade, phoneNumber);
    }
}
```

객체 생성을 단계별로 구성하거나 구성 단계를 지연하거나 재귀적으로 생성을 처리 할수 있다. 즉, 빌더를 재사용 함으로써 객체 생성을 주도적으로 지연할 수 있는 것이다.

현업에서 불변 객체를 이용해 개발해야 하는 이유로는 다음과 같다.

불변 객체는 Thread-Safe 하여 동기화를 고려하지 않아도 된다
만일 가변 객체를 통해 작업을 하는 도중 예외(Exception)가 발생하면 해당 객체가 불안정한 상태에 빠질 수 있어 또 다른 에러를 유발할 수 있는 위험성이 있기 때문이다.
불변 객체로 구성하면 다른 사람이 개발한 함수를 위험없이 이용을 보장할 수 있어 협업에도 유지보수에도 유용하다.

@Builder는 GOF의 디렉터 빌더가 아닌 심플 빌더 패턴을 다룬다는 점은 유의

필수 파라미터 builder

```java
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
class Person {
    private final String name;
    private final String age;
    private final String gender;
    private final String job;
    private final String birthday;
    private final String address;

    // 필수 파라미터 빌더 메서드 구현
    public static PersonBuilder builder(String name, String age) {
        // 빌더의 파라미터 검증
        if(name == null || age == null)
            throw new IllegalArgumentException("필수 파라미터 누락");

        // 필수 파라미터를 미리 빌드한 빌더 객체를 반환 (지연 빌더 원리)
        return new PersonBuilder().name(name).age(age);
    }
}

```

https://dev-youngjun.tistory.com/197
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EB%B9%8C%EB%8D%94Builder-%ED%8C%A8%ED%84%B4-%EB%81%9D%ED%8C%90%EC%99%95-%EC%A0%95%EB%A6%AC
