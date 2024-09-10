package com.example.Test.services;

import com.example.Test.dao.ItemDao;
import com.example.Test.dao.OrderDao;
import com.example.Test.dto.Item;
import com.example.Test.dto.Order;
import com.example.Test.repo.OrderRepo;
import com.example.Test.util.EncodingUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepo orderRepository;
    
    @Autowired
    private EncodingUtil encodingUtil;
    
    @Override
    public Order createOrder(OrderDao orderDao, String userId) {
        Order order = mapOrderDaoToOrder(orderDao);
        order.setUserId(userId);  // Assuming the setter is named setUserId
        

//        try {
//            Order savedOrder = orderRepository.save(order);
//            logger.info("Order created successfully with ID: {}", savedOrder.getId());
//            return savedOrder;
//        } catch (Exception e) {
//            logger.error("Failed to save order", e);
//            throw new RuntimeException("Failed to save order: " + e.getMessage());
//        }
//    }
        try {
            // Save the order first without the QR code ID
            Order savedOrder = orderRepository.save(order);
            logger.info("Order created successfully with ID: {}", savedOrder.getId());

            // Generate QR code ID using StringBuffer
            String qrcodeId = encodingUtil.generateQRCodeId(savedOrder.getId());

            // Update the saved order with the generated QR code ID
            savedOrder.setQrcode_id(qrcodeId);
            Order updatedOrder = orderRepository.save(savedOrder);

            logger.info("QR code ID generated and saved successfully: {}", qrcodeId);

            return updatedOrder;
        } catch (Exception e) {
            logger.error("Failed to create order", e);
            throw new RuntimeException("Failed to create order: " + e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrdersForUser(String userId) {
        logger.info("Fetching all orders for user ID: {}", userId);
        List<Order> orders = orderRepository.findByUserId(userId);
        logger.info("Found {} orders for user ID: {}", orders.size(), userId);
        return orders;
    }

    @Override
    public Order updateOrderForUser(Order order, String userId) {
        logger.info("Updating order for user ID: {}", userId);
        return orderRepository.findByIdAndUserId(order.getId(), userId)
            .map(existingOrder -> {
                // Update the existing order with new values
                // ... (update fields as before)
                Order updatedOrder = orderRepository.save(existingOrder);
                logger.info("Order updated successfully with ID: {}", updatedOrder.getId());
                return updatedOrder;
            }).orElseThrow(() -> {
                logger.warn("Order not found for user ID: {} and order ID: {}", userId, order.getId());
                return new RuntimeException("Order not found for the user");
            });
    }

    @Override
    public boolean deleteOrderForUser(String userId) {
        logger.info("Deleting order for user ID: {}", userId);
        Optional<Order> orderOptional = orderRepository.findFirstByUserId(userId);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
            logger.info("Order deleted successfully for user ID: {}", userId);
            return true;
        } else {
            logger.warn("No order found for user ID: {}", userId);
            return false;
        }
    }

    private Order mapOrderDaoToOrder(OrderDao orderDao) {
        Order order = new Order();
        order.setEvent_id(orderDao.getEvent_id());
        order.setEvent_date(orderDao.getEvent_date());
        order.setUserId(orderDao.getUserId());
        order.setPurchaser_name(orderDao.getPurchaser_name());
        order.setTotalamount(orderDao.getTotalamount());
        order.setDiscounted_amount(orderDao.getDiscounted_amount());
        order.setCoupon_discount(orderDao.getCoupon_discount());
        order.setQrcode_id(orderDao.getQrcode_id());
        order.setQr_date(orderDao.getQr_date());
        order.setPayment_mode(orderDao.getPayment_mode());
        order.setPurchase_date(orderDao.getPurchase_date());
        order.setPayment_status(orderDao.getPayment_status());
        order.setService_charges(orderDao.getService_charges());
        order.setNoofchildren(orderDao.getNoofchildren());
        order.setBirthyear(orderDao.getBirthyear());
        order.setLocation(orderDao.getLocation());
        order.setIsUsed(orderDao.getIsUsed());
        order.setCreatedAt(orderDao.getCreatedAt());
        order.setUpdatedAt(orderDao.getUpdatedAt());

        List<Item> items = new ArrayList<>();
        for (ItemDao itemDao : orderDao.getItems()) {
            Item item = new Item();
            item.setTicket_type(itemDao.getTicket_type());
            item.setNumberofseats(itemDao.getNumberofseats());
            item.setPrice(itemDao.getPrice());
            items.add(item);
        }
        order.setItems(items);

        if (orderDao.getImageArray() != null) {
            order.setImageArray(orderDao.getImageArray());
        }

        return order;
    }
    
    
}