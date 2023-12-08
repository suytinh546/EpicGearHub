package com.store.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.demo.entity.*;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{
	@Query("SELECT O FROM OrderDetail O where O.orders.id =?1")
	List<OrderDetail> findByOrderId(Integer id);

}
