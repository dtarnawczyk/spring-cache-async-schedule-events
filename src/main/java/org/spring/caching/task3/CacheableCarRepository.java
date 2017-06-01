package org.spring.caching.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CacheableCarRepository implements CarRepository, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(CacheableCarRepository.class);

    private CarRepository self;

    private final Collection<Car> cars = new HashSet<>(Arrays.asList(
            new Car(1l, "red", "car1"),   //
            new Car(2l, "blue", "car2"),  //
            new Car(3l, "yellow", "car3"),      //
            new Car(4l, "black", "car4"),       //
            new Car(5l, "white", "car5"),     //
            new Car(6l, "green", "car6"),     //
            new Car(7l, "red", "car7"),     //
            new Car(8l, "red", "car8"),     //
            new Car(9l, "black", "car9"),     //
            new Car(10l, "red", "car10")
    ));

    @Override
    public Collection<Car> getByIds(Long... id) {
        Set<Car> subSet = new HashSet<>();
        for(Long i : id){
            Car car = self.getById(i);
            if(car != null) subSet.add(car);
        }
        return subSet;
    }

    @Override
    @Cacheable(value = "cars", key = "'all'")
    public Collection<Car> getAll() {
        LOG.debug("*** Fetching all cars ***");
        return new TreeSet<>(cars);
    }

    @Override
    @Cacheable("cars")
    public Car getById(Long id) {
        LOG.debug("*** Searching for car with id [{}] ***", id);
        return cars.stream().filter(car -> car.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    @CachePut(value = "cars", key = "#car.id")
    @CacheEvict(value = "cars", key = "'all'")
    public Car add(Car car) {
        LOG.debug("> Adding car [{}]", car);
        cars.add(car);
        return car;
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "cars", key = "#car.id"),
        @CacheEvict(value = "cars", key = "'all'")
    })

    public void delete(Car car) {
        LOG.debug("> Deleting car [{}]", car);
        cars.remove(car);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        self = applicationContext.getBean(CarRepository.class);
    }

}
