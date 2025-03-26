package com.learn.ms.kafka.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;
}
