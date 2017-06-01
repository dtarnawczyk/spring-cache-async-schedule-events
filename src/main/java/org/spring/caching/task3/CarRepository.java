package org.spring.caching.task3;

import java.util.Collection;

public interface CarRepository {

    Car getById(Long id);

    Car add(Car car);

    void delete(Car car);

    Collection<Car> getAll();

    Collection<Car> getByIds(Long... id);

}
