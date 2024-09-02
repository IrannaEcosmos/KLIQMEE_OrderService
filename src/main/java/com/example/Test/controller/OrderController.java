package com.example.Test.controller;

import com.example.Test.dao.OrderDao;
import com.example.Test.dto.Order;
import com.example.Test.services.OrderService;
import com.example.Test.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDao orderDao, @RequestHeader("Authorization") String tokenHeader) {
        logger.info("Received request to create order");

        String token = tokenHeader.replace("Bearer ", "");
        logger.debug("Received token: {}", token);

        try {
            String userId = jwtUtil.extractUserId(token);
            logger.debug("Extracted userId: {}", userId);

            Order order = orderService.createOrder(orderDao, userId);
            logger.info("Order created successfully with ID: {}", order.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (RuntimeException e) {
            logger.error("Error processing order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while creating order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the order");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization") String tokenHeader) {
        logger.info("Received request to get all orders");

        String token = tokenHeader.replace("Bearer ", "");
        logger.debug("Received token: {}", token);

        try {
            String userId = jwtUtil.extractUserId(token);
            logger.debug("Extracted userId: {}", userId);

            List<Order> orders = orderService.getAllOrdersForUser(userId);
            logger.info("Retrieved {} orders for user: {}", orders.size(), userId);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            logger.error("Error retrieving orders: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while retrieving orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while retrieving the orders");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, @RequestHeader("Authorization") String tokenHeader) {
        logger.info("Received request to update order");

        String token = tokenHeader.replace("Bearer ", "");
        logger.debug("Received token: {}", token);

        try {
            String userId = jwtUtil.extractUserId(token);
            logger.debug("Extracted userId: {}", userId);

            Order updatedOrder = orderService.updateOrderForUser(order, userId);
            logger.info("Order updated successfully for user: {}", userId);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            logger.error("Error updating order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating the order");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder(@RequestHeader("Authorization") String tokenHeader) {
        logger.info("Received request to delete order");

        String token = tokenHeader.replace("Bearer ", "");
        logger.debug("Received token: {}", token);

        try {
            String userId = jwtUtil.extractUserId(token);
            logger.debug("Extracted userId: {}", userId);

            boolean deleted = orderService.deleteOrderForUser(userId);
            if (deleted) {
                logger.info("Order deleted successfully for user: {}", userId);
                return ResponseEntity.ok().body("Order deleted successfully");
            } else {
                logger.warn("No order found for user: {}", userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No order found for the user");
            }
        } catch (RuntimeException e) {
            logger.error("Error deleting order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the order");
        }
    }
}