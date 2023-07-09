package tobyspring.helloboot;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    private final HelloService helloService; //final은 재할당이 불가능하기 때문에 처음 정의했을 때 초기화를 해주거나 적어도 생성자에서 초기화 해줘야 한다

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name){
        if(name == null|| name.trim().length()==0) throw new IllegalArgumentException();
        return helloService.sayHello(name);
    }
}

