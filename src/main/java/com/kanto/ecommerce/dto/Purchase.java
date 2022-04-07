package com.kanto.ecommerce.dto;

import com.kanto.ecommerce.entity.Address;
import com.kanto.ecommerce.entity.Customer;
import com.kanto.ecommerce.entity.Order;
import com.kanto.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
