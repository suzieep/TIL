package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController(){
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Test");
        Assertions.assertThat(ret).isEqualTo("Test");
    }

    //null과 빈 문자열
    @Test
    void failHelloController(){
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(()->{
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(()->{
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }


}
