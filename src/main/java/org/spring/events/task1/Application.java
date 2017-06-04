package org.spring.events.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DataSynchronizer synchronizer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }

    @Scheduled(cron = "")
    public void schedulerTask(){
        synchronizer.synchronizeData();
    }


}
