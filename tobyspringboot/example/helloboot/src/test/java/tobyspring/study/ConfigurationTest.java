package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void configuration(){

//        Common common = new Common();
//        Assertions.assertThat(new Common()).isSameAs(common);

//        MyConfig myConfig = new MyConfig();
//        MyConfig bean1 = myConfig.bean1();
//        MyConfig bean2 = myConfig.bean2();
    AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext();
    ac.register(MyConfig.class);
    ac.refresh();
    // 하나의 빈을 두 개 이상의 다른 빈에서 의존하고 있다면 매번 만드니까 proxy 만들어서 기능을 확장을 해준다
    Bean1 bean1 = ac.getBean(Bean1.class);
    Bean2 bean2 = ac.getBean(Bean2.class);
    Assertions.assertThat(bean1.common).isSameAs(bean2.common);//True
    }

    @Test
    void proxyCommonMethod(){
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }


    static class MyConfigProxy extends MyConfig {
        private Common common;
        @Override
        Common common() {
            if (this.common==null) this.common = super.common();
            return this.common;
        }
    }

    // ex EnableSchduling 매번 프록시 만들어서 해 줄 필요가 없으면 false로 두고 사용하면 된다
    @Configuration//(proxyBeanMethods = false) 이때는 component로 바꿔도 상관 없음
    //false 일때는 method 직접 호출하면 안됨
    static class MyConfig {
        @Bean
        Common common(){
            return new Common();
        }
        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }
        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }
    // Bean1 <-- Common
    static class Bean1{
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }
    // Bean2 <-- Common
    static class Bean2{
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }

}
