package com.store.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.demo.DAO.RoleDAO;
import com.store.demo.entity.Role;
import com.store.demo.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO rdao;
	@Override
	public Role findById(String string) {
		// TODO Auto-generated method stub
		return rdao.findById(string).get();
	}

}
