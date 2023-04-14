package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration //구성정보를 가지고 있는 class다 이 안에 bean annotation이 붙은 factory method가 있겠구나 생각하고 조회하게 됨
@ComponentScan //이 클래스가 있는 패키지부터 하위 패키지를 뒤져서 component가 붙어있는 모든 클래스 찾아 -> 매번 구성정보 저장할 필요는 없음(편해서 표준으로 사용중), 하지만 어떤 클래스가 붙는지 바로 위계구조를 확인하기가 어려운 단점이 있음
public class HellobootApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		return new TomcatServletWebServerFactory();
	}
	@Bean
	public DispatcherServlet dispatcherServlet(){
		return new DispatcherServlet();
	}
	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class,args);

	}


}
