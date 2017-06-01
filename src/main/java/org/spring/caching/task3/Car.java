package org.spring.caching.task3;

import java.util.Objects;

public class Car implements Comparable<Car> {

    private final Long id;

    private final String name;

    private final String color;

    public Car(Long id, String color, String name) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
            "color='" + color + '\'' +
            ", id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public int compareTo(Car o) {
        return id.compareTo(o.getId());
    }

}
