package com.store.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping("/Gear/Admin/adminproduct")
	public String admin(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "Gear/Admin/adminproduct";
	}

	@GetMapping("/Gear/Admin/product-create")
	public String showCreateForm(Model model) {
		// Create an empty Product object to bind with the form
		Product product = new Product();
		model.addAttribute("product", product);
		return "Gear/Admin/product-create";
	}

	@PostMapping("/Gear/Admin/create")
	public String createProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) {
		// Add validation logic if needed
		if (!file.isEmpty()) {
			try {
				productService.create(product); // Assuming you have a save method in the productService
				Product productUpdate = productService.findById(product.getId());
				productUpdate.setImage("ID" + productUpdate.getId() + ".jpg");
				productService.update(productUpdate);
				// Lấy tên và đuôi của file
				String originalFilename = file.getOriginalFilename();
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

				// Sửa tên và đuôi của file
				List<Product> list = pdao.findAll();
				String modifiedFileName = productUpdate.getImage();

				// Đặt vị trí cho file (lưu trong thư mục ngoài project)
				String uploadDir = "C:\\Users\\suyti\\Documents\\workspace3\\EpicGearHub\\src\\main\\resources\\static\\assets\\imgproduct";
				File uploadPath = new File(uploadDir);

				if (!uploadPath.exists()) {
					uploadPath.mkdirs();
				}

				File dest = new File(uploadPath.getAbsolutePath() + File.separator + modifiedFileName);
				file.transferTo(dest);
				System.out.print(product.getName());

			} catch (IOException e) {
				e.printStackTrace();

			}
		} else {

		}
		return "redirect:/Gear/Admin/product-create";
	}

	@RequestMapping("/Gear/Admin/product-edit/{id}")
	public String productedit(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "Gear/Admin/product-edit";
	}

	@PostMapping("/Gear/Admin/Admin/update/{id}")
	public String updateProduct(@ModelAttribute("product") Product updateProduct, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		System.out.println(updateProduct.getCategory().getName());

		Product productUpdate = product;
		productUpdate.setName((updateProduct).getName());
		productUpdate.setPrice((updateProduct).getPrice());
//        productUpdate.setCategory.((updateProduct).getCategory().getId());
		productUpdate.setBrand((updateProduct).getBrand());
		productUpdate.setCategory((updateProduct.getCategory()));
		productService.update(productUpdate); // Assuming you have an update method in the productService
		System.out.println("tới đây vẫn chạy");
		return "redirect:/Gear/Admin/adminproduct"; // Redirect to the product list page
	}

	@GetMapping("/Gear/Admin/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		pdao.deleteById(id);
		return "redirect:/Gear/Admin/adminproduct";
	}

	@RequestMapping("/Gear/Admin/adminaccount")
	public String adminaccount(Model model) {
		List<Authorities> list = authoritiesService.findAll();
		model.addAttribute("authorities", list);
		return "Gear/Admin/adminaccount";
	}

	@GetMapping("/Gear/Admin/deleteaccount/{id}")
	public String deleteAcount(@PathVariable("id") String id) {
		adao.deleteById(id);
		return "redirect:/Gear/Admin/adminaccount";
	}

	@RequestMapping("/Gear/Admin/admin-order")
	public String adminorder(Model model) {
		List<OrderDetail> list = orderdetailService.findAll();
		model.addAttribute("Orders", list);
		return "Gear/Admin/admin-order";
	}

	@GetMapping("/Gear/Admin/accountedit/{username}")
	public String editaccount(Model model, @PathVariable("username") String username) {
		Authorities authorities = authoritiesService.findByUsername(username);
		model.addAttribute("authorities", authorities);
		return "Gear/Admin/accountedit";
	}

	@PostMapping("/Gear/Admin/accountupdate/{username}")
	public String updateAccount(@ModelAttribute("authorities") Authorities updateAuthorities,
			@PathVariable("username") String username) {
		Authorities authorities = authoritiesService.findByUsername(username);
		Authorities authoritiesUpdate = authorities;
		authoritiesUpdate.setRole((updateAuthorities).getRole());
		authoritiesService.update(authoritiesUpdate); // Assuming you have an update method in the productService
		System.out.println("tới đây vẫn chạy");
		return "redirect:/Gear/Admin/adminaccount"; // Redirect to the product list page
	}
}
