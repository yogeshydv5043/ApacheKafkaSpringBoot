package com.learn.ms.kafka.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;
}
