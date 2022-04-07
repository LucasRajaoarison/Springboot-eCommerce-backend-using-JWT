package com.kanto.ecommerce.service;

import com.kanto.ecommerce.dao.CustomerRepository;
import com.kanto.ecommerce.dto.Purchase;
import com.kanto.ecommerce.dto.PurchaseResponse;
import com.kanto.ecommerce.entity.Customer;
import com.kanto.ecommerce.entity.Order;
import com.kanto.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve the order info from dto
        Order order = purchase.getOrder();

        //generate tracking number
        String orderTrackingNumber = getOrderTrackingNumber() ;
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item) );

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        //check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer  customerDB = customerRepository.findByEmail(theEmail) ;

        if (customerDB != null) {
            customer = customerDB;
        }

        customer.add(order);

        //save to the database
        customerRepository.save(customer) ;

        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String getOrderTrackingNumber() {
        //generate a random UUID number
        //
        return UUID.randomUUID().toString();
    }
}
