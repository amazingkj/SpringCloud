package com.example.catalogservice.service;

import com.example.catalogservice.dto.OrderDto;
import com.example.catalogservice.jpa.OrderEntity;

public interface OrderService {
   OrderDto createOrder(OrderDto orderDetails);
   OrderDto getOrderByOrderId(String orderId);
   Iterable<OrderEntity> getOrderByUserId(String userId);


}
