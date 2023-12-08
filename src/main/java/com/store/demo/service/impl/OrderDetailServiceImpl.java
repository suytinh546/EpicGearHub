package com.store.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.demo.DAO.OrderDetailDAO;
import com.store.demo.entity.Order;
import com.store.demo.entity.OrderDetail;
import com.store.demo.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailDAO ddao;
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return ddao.findAll();
	}
	@Override
	public List<OrderDetail> findByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return ddao.findByOrderId(id);
	}
}
