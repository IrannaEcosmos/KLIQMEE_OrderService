package com.example.Test.services;

import java.util.List;
import java.util.Optional;

import com.example.Test.dao.OrderDao;
import com.example.Test.dto.Order;

public interface OrderService {

	Order createOrder(OrderDao orderDao, String userId);

	List<Order> getAllOrdersForUser(String userId);

	Order updateOrderForUser(Order order, String userId);

	boolean deleteOrderForUser(String userId);

}
