package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

        /**
         * ConfigurableApplicationContext는 AnnotationConfigApplicationContext의 상위 인터페이스이다
         * ConfigurableApplicationContext는 close를 제공한다
         * ApplicationContext는 close를 제공하지 않는다.
         */
    }

    @Configuration
    static class LifeCycleConfig{

        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://www.naver.com/");
            return networkClient;
        }
    }
}
