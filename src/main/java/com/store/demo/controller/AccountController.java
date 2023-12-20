package com.store.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.demo.DAO.AuthoritiesDAO;
import com.store.demo.entity.Account;
import com.store.demo.entity.Authorities;
import com.store.demo.entity.Role;
import com.store.demo.service.AccountService;
import com.store.demo.service.MyEmailService;
import com.store.demo.service.RoleService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	AuthoritiesDAO authodao;
	@Autowired
	RoleService roleService;
	@Autowired
	MyEmailService myemailservice;
	
	@RequestMapping("/Gear/user-create")//  goi ra form dang ky
	public String dangky(Model model ) {
		Account account = new Account();
		model.addAttribute("account",account);
		return "Gear/user-create";
	}
	@PostMapping("/Gear/usercreate")// sau khi nhấn nút đăng ký sẽ gọi phương thức này
	public String dangky(@ModelAttribute("account") Account account,Model model ) {
		if(account.getUsername() == null || account.getUsername().isEmpty() ||
			    account.getPassword() == null || account.getPassword().isEmpty() ||
			    account.getEmail() == null || account.getEmail().isEmpty()) {
			model.addAttribute("account",account);
			model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
			return "Gear/user-create";
		}else {
			accountService.create(account);
			String id="CUST";
			Role rolecust = roleService.findById(id);
			Authorities authorities = new Authorities();
			authorities.setAccount(account);
	        authorities.setRole(rolecust);
	        authodao.save(authorities);
			return "Gear/login";
		}
		
	}
	@RequestMapping("/Gear/doimk")
	public String doimk(Model model,HttpServletRequest request ) {
		model.addAttribute("account", accountService.findById(request.getRemoteUser()));
		return "Gear/doimk";
	}
	@PostMapping("/Gear/updatepass")
	public String updatepass(Model model,HttpServletRequest request,@RequestParam("password")String password,
								@RequestParam("newpassword1")String newpassword1,@RequestParam("newpassword2")String newpassword2 ) {
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		if(account.getPassword().equals(password)) {
			if(newpassword1.equals(newpassword2)) {
				account.setPassword(newpassword2);
				accountService.update(account);
				model.addAttribute("message", "Đổi mật khẩu thành công");
				return "Gear/doimksuccess"; 
			}else{
				model.addAttribute("message", "Mật khẩu mới không trùng khớp! vui lòng nhập lại");
				return "Gear/doimk"; 
			}
		}else {
			model.addAttribute("message", "Mật khẩu cũ không đúng! vui lòng nhập lại");
			return "Gear/doimk"; 
		}
	}
	
	@RequestMapping("/Gear/forgotpass")
	public String forgotpass() {	
		return "Gear/forgotpass";
	}
	@PostMapping("/Gear/forgotpass/sendtemppassword")
	public String sendtempassword(Model model,@RequestParam("username")String username) {
			Account account = accountService.findById(username);
			if(account==null) {
				model.addAttribute("message", "Không tìm thấy tài khoản của bạn");
				return "Gear/forgotpass"; 
			}else {
				account.setPassword("123456");
				accountService.update(account);
				myemailservice.sendEmail(account.getEmail(), "Mật khẩu tạm", "mật khẩu tạm của bạn là: 123456");
			}
			model.addAttribute("message", "Mật khẩu cũ không đúng! vui lòng nhập lại");
			return "Gear/doimksuccess"; 
	}
}
