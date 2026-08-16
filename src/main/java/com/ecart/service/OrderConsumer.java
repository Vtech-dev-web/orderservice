package com.ecart.service;
//import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    //@JmsListener(destination = "order-queue")
    public void consumeOrder(String orderId) {

        System.out.println("Received Order ID: " + orderId);

        // Payment processing
    }
}