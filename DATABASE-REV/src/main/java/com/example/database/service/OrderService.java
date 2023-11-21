package com.example.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.database.entity.Order;

@Service
public interface OrderService {
  
  List<Order> getOrders();
  Order getOrderById(Long id);
  Order creaOrder(Order order);

}
