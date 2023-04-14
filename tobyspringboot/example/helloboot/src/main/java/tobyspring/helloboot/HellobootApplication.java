package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
//@MySpringBootAnnotation
//@Configuration //구성정보를 가지고 있는 class다 이 안에 bean annotation이 붙은 factory method가 있겠구나 생각하고 조회하게 됨
//@ComponentScan
public class HellobootApplication {

//	@Bean
//	public ServletWebServerFactory servletWebServerFactory(){
//		return new TomcatServletWebServerFactory();
//	}
//	@Bean
//	public DispatcherServlet dispatcherServlet(){
//		return new DispatcherServlet();
//	}
	public static void main(String[] args) {
		SpringApplication.run(Config.class,args);

	}


}
