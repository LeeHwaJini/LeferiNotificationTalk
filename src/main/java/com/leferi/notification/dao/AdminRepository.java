package com.leferi.notification.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leferi.notification.model.Admin;
import com.leferi.notification.model.NotificationTemplete;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<NotificationTemplete>{
	public Optional<Admin> findOneByAdmId(String id);
}
