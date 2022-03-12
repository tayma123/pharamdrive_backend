package com.tplus.PharmasDriveAPI.service;
import com.tplus.PharmasDriveAPI.model.Order;
import com.tplus.PharmasDriveAPI.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getOrders(Pageable pageable);
    Order addOrder(Order poder);
    Order  getOrder(String id );
    void deleteOrder(String id );
    Order updateOrder(Order order);
    int countOrders();
    Long getCountOrdersByStatusFilter( String status);
}
