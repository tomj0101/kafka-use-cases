package com.github.tomj0101.springkafka.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Profile {
    private String name;
    private int number1;
    private float number2;
}
