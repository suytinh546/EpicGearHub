package com.store.demo.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.demo.entity.Account;
import com.store.demo.entity.Order;
import com.store.demo.service.AccountService;
import com.store.demo.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData, HttpServletRequest request) {
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		return orderService.create(orderData, account);
	}
}
