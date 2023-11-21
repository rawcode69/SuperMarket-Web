package com.example.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.database.entity.Order;
import com.example.database.service.OrderService;

@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/orders")
  public ResponseEntity<List<Order>> getAllOrders() {
    return ResponseEntity.status(200).body(orderService.getOrders());
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable long id) {
    return ResponseEntity.status(200).body(orderService.getOrderById(id));
  }

  @PostMapping("/orders")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {

    Order newOrder = orderService.creaOrder(order);

    return ResponseEntity.status(201).body(newOrder);
  }

}
