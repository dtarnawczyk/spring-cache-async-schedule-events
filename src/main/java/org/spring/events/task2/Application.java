package org.spring.events.task2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(myExecutor());
        return multicaster;
    }

    @Bean
    public Executor myExecutor() {
        ThreadPoolTaskExecutor myTaskExecutor = new ThreadPoolTaskExecutor();
        myTaskExecutor.setThreadNamePrefix("MyExecutorThread");
        myTaskExecutor.setCorePoolSize(1);
        myTaskExecutor.setMaxPoolSize(3);
        return myTaskExecutor;
    }


}
