package com.store.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.demo.DAO.AuthoritiesDAO;
import com.store.demo.DAO.RoleDAO;
import com.store.demo.entity.Account;
import com.store.demo.entity.Product;
import com.store.demo.entity.Role;
import com.store.demo.entity.Authorities;
import com.store.demo.service.AccountService;
import com.store.demo.service.MyEmailService;
import com.store.demo.service.ProductService;
import com.store.demo.service.RoleService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	AccountService accountService;
	@Autowired
	AuthoritiesDAO authodao;
	@Autowired
	RoleService roleService;
	@Autowired
	MyEmailService myemailservice;
	
//	@RequestMapping("/Gear/index")
//	public String list(Model model, @RequestParam("cid")Optional<String> cid) {
//		if (cid.isPresent()) {
//			List<Product> list = productService.findByCategoryId(cid.get());
//			model.addAttribute("items",list);	
//		}
//		else{
//			List<Product> list = productService.findAll();
//			model.addAttribute("items",list);
//		}
//		return "Gear/index";
//	}
	@RequestMapping("/Gear/ctsp/{id}")
	public String ctsp(Model model,@PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		System.out.println("tới đây vẫn chạy");
		model.addAttribute("item",item);
		return "Gear/ctsp";
	}	
	@RequestMapping("/Gear/product-detail/{id}")
	public String productdetail(Model model,@PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		System.out.println("tới đây vẫn chạy");
		model.addAttribute("item",item);
		List<Product> listcpu = productService.findByCategoryId("1");
		model.addAttribute("productcpu",listcpu);
		List<Product> listvga = productService.findByCategoryId("2");
		model.addAttribute("productvga",listvga);
		return "Gear/product-detail";
	}
	@RequestMapping("/Gear/home")
	public String home(Model model) {
		List<Product> listcpu = productService.findByCategoryId("1");
		model.addAttribute("productcpu",listcpu);
		List<Product> listvga = productService.findByCategoryId("2");
		model.addAttribute("productvga",listvga);
		return "Gear/home";
	}
	@RequestMapping("/Gear/index")
	public String index(Model model) {
		List<Product> listcpu = productService.findByCategoryId("1");
		model.addAttribute("productcpu",listcpu);
		List<Product> listvga = productService.findByCategoryId("2");
		model.addAttribute("productvga",listvga);
		return "Gear/index";
	}
	@RequestMapping("/Gear/list")
	public String phanloai(Model model, @RequestParam("cid")Optional<String> cid) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items",list);	
		}
		else{
			List<Product> list = productService.findAll();
			model.addAttribute("items",list);
		}
		return "Gear/list";
	}
	@GetMapping("/Gear/search")
	public String searchProduct(@RequestParam(value = "searchValue",required = false)String searchValue,Model model) {
		List<Product> list = productService.findByName(searchValue);
		model.addAttribute("product",list);
		return "Gear/product";
	}
	@RequestMapping("/Gear/user-create")
	public String dangky(Model model ) {
		Account account = new Account();
		model.addAttribute("account",account);
		return "Gear/user-create";
	}
	@PostMapping("/Gear/usercreate")
	public String dangky(@ModelAttribute("account") Account account ) {
		accountService.create(account);
		String id="CUST";
		Role rolecust = roleService.findById(id);
		Authorities authorities = new Authorities();
		List<Authorities> countautho = authodao.findAll();
		authorities.setId(countautho.size()+1);
		authorities.setAccount(account);
        authorities.setRole(rolecust);
        authodao.save(authorities);
		return "Gear/login";
	}
	@RequestMapping("/Gear/doimk")
	public String doimk(Model model,HttpServletRequest request ) {
		model.addAttribute("account", accountService.findById(request.getRemoteUser()));
		return "Gear/doimk";
	}
	@RequestMapping("/Gear/product")
	public String productlist(Model model, @RequestParam("cid")Optional<String> cid) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("product",list);	
		}
		else{
			List<Product> list = productService.findAll();
			model.addAttribute("product",list);
		}
		return "Gear/product";
	}
	@PostMapping("/Gear/updatepass")
	public String updatepass(Model model,HttpServletRequest request,@ModelAttribute("account") Account updateAccount,@RequestParam("password")String password,@RequestParam("newpassword1")String newpassword1,@RequestParam("newpassword2")String newpassword2 ) {
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
				return "Gear/doimksuccess"; 
			}
		}else {
			model.addAttribute("message", "Mật khẩu cũ không đúng! vui lòng nhập lại");
			return "Gear/doimksuccess"; 
		}
	}
	
	@RequestMapping("/Gear/about")
	public String about() {
		return "Gear/about";
	}
	
	@RequestMapping("/Gear/news")
	public String news() {
		return "Gear/news";
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
