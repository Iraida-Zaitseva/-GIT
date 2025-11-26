package com.example.springhibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "car_id", unique = true)
    private Car car;

    public User() {}

    public User(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Car getCar() { return car; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCar(Car car) { this.car = car; }
}
