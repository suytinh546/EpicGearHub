package com.store.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.demo.entity.Role;

@Service
public interface RoleService {

	Role findById(String id);

}
