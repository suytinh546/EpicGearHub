package com.store.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.demo.entity.*;

public interface AuthoritiesDAO extends JpaRepository<Authorities, String>{
	@Query("SELECT a  FROM Authorities a WHERE a.account.username = ?1")
	Authorities findByUsername(String username);

}
