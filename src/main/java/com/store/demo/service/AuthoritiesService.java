package com.store.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.demo.entity.Authorities;
import com.store.demo.entity.OrderDetail;
@Service
public interface AuthoritiesService {
	public List<Authorities> findAll();

	public Authorities findByUsername(String username);

	public void update(Authorities authoritiesUpdate);
	
}
