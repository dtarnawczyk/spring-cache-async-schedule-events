package org.spring.caching.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CarRepository carRepository;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        printCar(1L);
        printCar(1L);
        printCar(2L);
        printCar(2L);
        Car myNewCar = new Car(100L, "red", "new one");
        carRepository.add(myNewCar);
        printCar(myNewCar.getId());
        carRepository.delete(myNewCar);
        printCar(myNewCar.getId());

    }

    private void printCar(Long id) {
        LOG.info("> About to print car with id [{}]...", id);
        Car car = carRepository.getById(id);
        if (car != null) {
            LOG.info("> {}", car);
        } else {
            LOG.info("> Car not found");
        }
    }
}
