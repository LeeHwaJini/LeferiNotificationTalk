package com.leferi.notification.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leferi.notification.model.NotificationTemplete;

@RepositoryRestResource
public interface NotificationTempleteRepository extends JpaRepository<NotificationTemplete, Long>, JpaSpecificationExecutor<NotificationTemplete>{

}
