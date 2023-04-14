package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)//class에적용, type은 class, interface,enum에 지정할 때 type으로 지정
//@Configuration //구성정보를 가지고 있는 class다 이 안에 bean annotation이 붙은 factory method가 있겠구나 생각하고 조회하게 됨
//@ComponentScan //이 클래스가 있는 패키지부터 하위 패키지를 뒤져서 component가 붙어있는 모든 클래스 찾아 -> 매번 구성정보 저장할 필요는 없음(편해서 표준으로 사용중), 하지만 어떤 클래스가 붙는지 바로 위계구조를 확인하기가 어려운 단점이 있음
//public @interface MySpringBootAnnotation {
//}
