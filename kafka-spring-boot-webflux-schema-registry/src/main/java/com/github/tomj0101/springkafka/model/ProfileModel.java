package com.github.tomj0101.springkafka.model;

public class ProfileModel {
    private String name;
    private int number1;
    private float number2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public float getNumber2() {
        return number2;
    }

    public void setNumber2(float number2) {
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "name='" + name + '\'' +
                ", number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
