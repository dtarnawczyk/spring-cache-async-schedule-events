package org.spring.async.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Calculator distributionCalculator;

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

        Future<Map<Integer, Integer>> distributionMap = calculate();
        while(true){
            if(distributionMap.isDone()){
                LOG.info("Result [{}]", distributionMap.get());
                break;
            }
        }

    }

    @Async
    public Future<Map<Integer, Integer>> calculate() {
        Map<Integer, Integer> distributionMap = distributionCalculator.calculateRandomDistribution();
        return new AsyncResult<>(distributionMap);
    }

/*

Alternatywnie oznaczamy metode calculateRandomDistribution jak @Async i ponizszymi metodami wyciagamy wartosc

    @Override
    public void run(String... strings) throws Exception {

        Set<Future<Map<Integer, Integer>>> futuresSet = new HashSet<>();
        for (int i = 0; i< 100; i++) {
            futuresSet.add(distributionCalculator.calculateRandomDistribution());
        }

        Map<Integer,Integer> result = new HashMap<>();
        futuresSet.stream().forEach(future -> {
            try {
                harvestResult(future.get(), result);
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("Error while processing", e);
            }
        });
        LOG.info("Result [{}]", result);

    }

    private void harvestResult(Map<Integer, Integer> partResult, Map<Integer, Integer> fullResult) {
        partResult.entrySet().stream().forEach(entry -> {
            Integer key = entry.getKey();
            Integer count = entry.getValue();
            if (fullResult.containsKey(key)) {
                fullResult.put(key, fullResult.get(key) + count);
            } else {
                fullResult.put(key, count);
            }
        });
    }
*/

}
