package com.pharamdrive.repository;

import com.pharamdrive.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository< Notification,String> {
}
