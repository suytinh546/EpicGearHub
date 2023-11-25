package com.store.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.demo.DAO.AuthoritiesDAO;
import com.store.demo.entity.Authorities;
import com.store.demo.service.AuthoritiesService;
@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

	@Autowired
	AuthoritiesDAO audao;

	public List<Authorities> findAll() {
		// TODO Auto-generated method stub
		return audao.findAll();
	}

	@Override
	public Authorities findByUsername(String username) {
		// TODO Auto-generated method stub
		return audao.findByUsername(username);
	}

	public void update(Authorities authoritiesUpdate) {
		// TODO Auto-generated method stub
		audao.save(authoritiesUpdate);
	}


}
