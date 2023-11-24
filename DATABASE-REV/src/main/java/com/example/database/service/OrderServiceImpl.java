package com.example.database.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.dto.OrderDTO;
import com.example.database.entity.Order;
import com.example.database.entity.Product;
import com.example.database.repository.OrderRepository;
import com.example.database.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  @Override
  public Order createOrder(OrderDTO orderDto) {

    Order order = new Order();

    List<Long> products = orderDto.getProducts();

    Set<Product> productsSet = new HashSet<>();

    order.setTotal(0.0);

    for (Long productId : products) {

      Product product = productRepository.findById(productId).orElse(null);

      if (product != null) {
        productsSet.add(product);
        order.setTotal(order.getTotal() + product.getPrice());
      }

    }

    Double tax = (order.getTotal()/100) * 15;

    order.setTax(tax);
    order.setOrderTime(LocalDateTime.now());
    order.setProducts(productsSet);

    return orderRepository.save(order);
  }

}
