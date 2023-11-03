package com.store.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.demo.entity.Product;
import com.store.demo.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
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
}
