package org.spring.async.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.setThreadNamePrefix(">>MyScheduler-thread-");
        return taskScheduler;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private HtmlReader reader;

    @Override
    public void run(String... strings) throws Exception {
        reader.schedule();
    }
}
