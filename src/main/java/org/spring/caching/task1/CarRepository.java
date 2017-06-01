package org.spring.caching.task1;

public interface CarRepository {

    Car getById(Long id);

    Car add(Car car);

    void delete(Car car);

}
