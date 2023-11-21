package com.example.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.entity.Order;
import com.example.database.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  @Override
  public Order creaOrder(Order order) {
    return orderRepository.save(order);
  }

}
