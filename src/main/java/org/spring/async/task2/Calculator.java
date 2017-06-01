package org.spring.async.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Calculator {

    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

    public Map<Integer, Integer> calculateRandomDistribution() {
        Map<Integer, Integer> countMap = new HashMap<>();
        Random random = new Random();

        for (int i=0; i < 1_000_000; i++) {
            Integer randomInteger = random.nextInt(10);

            if (countMap.containsKey(randomInteger)) {
                countMap.put(randomInteger, countMap.get(randomInteger) + 1);
            } else {
                countMap.put(randomInteger, 1);
            }
        }
        LOG.debug("Calculation finished");
        return countMap;
    }



}
