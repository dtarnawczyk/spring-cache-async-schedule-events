package org.spring.async.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    private ImportantThingManager importantThingManager;

    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor taskExecutor =  new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setThreadNamePrefix(">>>MyExecutor-");
        return taskExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < 100 ; i++) {
            importantThingManager.doImportantThing();
        }
    }
}
