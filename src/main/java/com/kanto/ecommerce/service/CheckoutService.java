package com.kanto.ecommerce.service;

import com.kanto.ecommerce.dto.Purchase;
import com.kanto.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
