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
	
	@RequestMapping("/Gear/index")
	public String list(Model model, @RequestParam("cid")Optional<String> cid) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items",list);	
		}
		else{
			List<Product> list = productService.findAll();
			model.addAttribute("items",list);
		}
		return "Gear/index";
	}
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
		model.addAttribute("items",list);
		return "Gear/list";
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
	public String updatepass(HttpServletRequest request,@ModelAttribute("account") Account updateAccount) {
		String username = request.getRemoteUser();
		Account account = accountService.findById(username);
		Account accountupdate = account;
		accountupdate.setPassword((updateAccount).getPassword());
		accountService.update(accountupdate);
		return "Gear/doimksuccess"; 
	}
	
}
