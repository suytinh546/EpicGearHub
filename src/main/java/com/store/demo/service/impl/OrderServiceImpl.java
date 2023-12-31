package com.store.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.demo.DAO.AccountDAO;
import com.store.demo.DAO.OrderDAO;
import com.store.demo.DAO.OrderDetailDAO;
import com.store.demo.entity.Account;
import com.store.demo.entity.Order;
import com.store.demo.entity.OrderDetail;
import com.store.demo.service.AccountService;
import com.store.demo.service.MyEmailService;
import com.store.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Autowired
	AccountDAO adao;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	MyEmailService myemailservice;
	
	
	public Order create(JsonNode orderData, Account account) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		order.setAccount(account);
//		Account account = order.getAccount();
//		System.out.print(account.getUsername() + order.getAccount().getUsername());
//		Account accdao = accountService.findById(order.getAccount().getUsername());
//		account.setPassword(accdao.getPassword());
//		account.setEmail(accdao.getEmail());
//		order.setAccount(account);
		//accountService.update(account);
		System.out.print(order.getAccount().getEmail());
		myemailservice.sendEmail(order.getAccount().getEmail(), "Đặt hành thành công", "Đơn hàng của bạn đã được đặt thành công");
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrders(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public Order create(JsonNode orderData) {
		// TODO Auto-generated method stub
		return null;
	}
	

}