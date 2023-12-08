package com.store.demo.service;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.demo.entity.Order;
import com.store.demo.entity.OrderDetail;
@Service
public interface OrderDetailService {
	
	public List<OrderDetail> findAll();

	public List<OrderDetail> findByOrderId(Integer id);
}
