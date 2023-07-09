package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary //2개 주입할 때 이거 먼저 주입
public class HelloDecorator implements HelloService {
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService){
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name){
        return "*"+helloService.sayHello(name)+"*";
    }
}
