package com.store.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.demo.DAO.AccountDAO;
import com.store.demo.DAO.OrderDAO;
import com.store.demo.DAO.ProductDAO;
import com.store.demo.entity.Authorities;
import com.store.demo.entity.OrderDetail;
import com.store.demo.entity.Product;
import com.store.demo.service.AccountService;
import com.store.demo.service.AuthoritiesService;
import com.store.demo.service.OrderDetailService;
import com.store.demo.service.ProductService;

@Controller
public class AdminController {
	@Autowired
	ProductService productService;
	@Autowired
	AccountService accountService;
	@Autowired
	OrderDetailService orderdetailService;
	@Autowired
	AuthoritiesService authoritiesService;
	@Autowired
	ProductDAO pdao;
	@Autowired
	OrderDAO odao;
	@Autowired
	AccountDAO adao;
	@RequestMapping("/Gear/adminproduct")
	public String admin(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items",list);
		return "Gear/adminproduct";
	}
	@GetMapping("/Gear/product-create")
	public String showCreateForm(Model model) {
	    // Create an empty Product object to bind with the form
	    Product product = new Product();
	    model.addAttribute("product", product);
	    return "Gear/product-create";
	}

    @PostMapping("/Gear/create")
    public String createProduct(@ModelAttribute("product") Product product) {
        // Add validation logic if needed
    	System.out.print(product.getName());
        productService.create(product); // Assuming you have a save method in the productService
        return "redirect:/Gear/product-create";
    }
    @RequestMapping("/Gear/product-edit/{id}")
	public String productedit(Model model,@PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("product",product);
		return "Gear/product-edit";
	}
    @PostMapping("/Gear/update/{id}")
    public String updateProduct(@ModelAttribute("product") Product  updateProduct,@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        Product productUpdate = product;
        productUpdate.setName((updateProduct).getName());
        productUpdate.setPrice((updateProduct).getPrice());
//        productUpdate.setCategory.((updateProduct).getCategory().getId());
        productUpdate.setBrand((updateProduct).getBrand());
        productService.update(productUpdate); // Assuming you have an update method in the productService
		System.out.println("tới đây vẫn chạy");
        return "redirect:/Gear/adminproduct"; // Redirect to the product list page
    }
    @GetMapping("/Gear/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
    	pdao.deleteById(id);
    	return  "redirect:/Gear/adminproduct";
    }
    @RequestMapping("/Gear/adminaccount")
    	public String adminaccount(Model model) {
    	List<Authorities> list = authoritiesService.findAll();
		model.addAttribute("authorities",list);
    	return "Gear/adminaccount";
    }
    @GetMapping("/Gear/deleteaccount/{id}")
    public String deleteAcount(@PathVariable("id") String id) {
    	adao.deleteById(id);
    	return  "redirect:/Gear/adminaccount";
    }
    @RequestMapping("/Gear/admin-order")
    public String adminorder(Model model) {
    	List<OrderDetail> list = orderdetailService.findAll();
    	model.addAttribute("Orders",list);
    	return "Gear/admin-order";
    }
    @GetMapping("/Gear/accountedit/{username}")
    public String editaccount(Model model,@PathVariable("username") String username) {
    	Authorities authorities = authoritiesService.findByUsername(username);
    	model.addAttribute("authorities",authorities);
    	return "Gear/accountedit";
    }
    @PostMapping("/Gear/accountupdate/{username}")
    public String updateAccount(@ModelAttribute("authorities") Authorities  updateAuthorities,@PathVariable("username") String username) {
        Authorities authorities = authoritiesService.findByUsername(username);
        Authorities authoritiesUpdate = authorities;
        authoritiesUpdate.setRole((updateAuthorities).getRole());
        authoritiesService.update(authoritiesUpdate); // Assuming you have an update method in the productService
		System.out.println("tới đây vẫn chạy");
        return "redirect:/Gear/adminaccount"; // Redirect to the product list page
    }
}
