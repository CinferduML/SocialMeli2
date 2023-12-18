package com.sprint.be_java_hisp_w23_g04.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private LocalDate date;
    private Product product;
    private int category;
    private double price;

    public Post (LocalDate date, Product product, int category, double price) {
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }
}
