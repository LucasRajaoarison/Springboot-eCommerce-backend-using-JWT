package com.kanto.ecommerce.controller;

import com.kanto.ecommerce.dto.Purchase;
import com.kanto.ecommerce.dto.PurchaseResponse;
import com.kanto.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;


    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase) ;

        return purchaseResponse;
    }

}
