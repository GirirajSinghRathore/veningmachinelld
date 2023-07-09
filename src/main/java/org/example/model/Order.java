package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Slot slot;
    private Product product;
    private int price;
    private OrderStatus orderStatus;
}
