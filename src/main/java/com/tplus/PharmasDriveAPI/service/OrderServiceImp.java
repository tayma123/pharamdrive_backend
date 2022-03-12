package com.tplus.PharmasDriveAPI.service;

import java.util.Optional;

import com.tplus.PharmasDriveAPI.model.Order;
import com.tplus.PharmasDriveAPI.repository.OrderRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
@Service
public class OrderServiceImp  implements OrderService{


    @Autowired
    private OrderRepository orderRepository ;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
        
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
        
    }

    @Override
    public Order getOrder(String id) {
        Optional<Order> order =  orderRepository.findById(id);
        if (order.isPresent())
            return order.get();
        throw new RuntimeException("Donation is not found for the id "+ id);
		
    }

    @Override
    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(Order newOrder) {
        return orderRepository.findById(newOrder.getObjectId())
        .map(order ->  {
            order.setNameUser(newOrder.getNameUser());
            order.setDrog(newOrder.getDrog());
            order.setAmount(newOrder.getAmount());
            order.setDateHour(newOrder.getDateHour());
            order.setStatus(newOrder.getStatus());
        return orderRepository.save(order);

        }) .orElseGet(() -> {
            newOrder.setObjectId(new ObjectId(newOrder.getObjectId()));
            return orderRepository.save(newOrder);
          });
    }

    @Override
    public int countOrders() {
        
        return (int) orderRepository.count() ;
    }

    @Override
    public Long getCountOrdersByStatusFilter( String status) {

      //  Query query=Query.query( new Criteria().andOperator(
      //  Criteria.where("status").is(status)
    
      //  )
   // );
   Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
    //return orderRepository.countByFilter(status) ;
  return  orderRepository.countFindByStatus(status);
  
}

}