package com.store.demo.controller;

import java.util.List;

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

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import com.store.demo.entity.Account;
import com.store.demo.entity.Order;
import com.store.demo.entity.OrderDetail;

import com.store.demo.service.AccountService;
import com.store.demo.service.OrderService;
import com.store.demo.service.OrderDetailService;
import com.store.demo.service.PaypalService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	AccountService accountService;
	@Autowired
	PaypalService service;
	public static final String SUCCESS_URL = "Gear/order/Paymentsuccsess";
	public static final String CANCEL_URL = "pay/cancel";
	@RequestMapping("/Gear/order/checkout")
	public String checkout() {
		return "Gear/order/checkout";
	}
	@RequestMapping("/Gear/order/checkoutpaypal")
	public String checkoutpp() {
		return "Gear/order/checkoutpaypal";
	}
	@PostMapping("/Gear/order/payment")
	public String payment(@RequestParam("total")Double total) {
		try {
			System.out.println(total);
			Payment payment = service.createPayment(total, "USD", "paypal",
					"sale", "dat hang", "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}

		} catch (PayPalRESTException e) {

			e.printStackTrace();
		}
		return "redirect:/";
	}

	 @GetMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "cancel";
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "Gear/order/Paymentsuccsess";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "Gear/order/Paymentsuccsess";
	    }
	@RequestMapping("/Gear/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("order", orderService.findById(id));
		List<OrderDetail> orderdetaillist = orderDetailService.findByOrderId(id);
		model.addAttribute("orderDetails", orderdetaillist);
		return "Gear/order/detail";
	}
	@RequestMapping("/Gear/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		List<Order> order = orderService.findByUsername(username);
		model.addAttribute("orders", order);
		return "Gear/order/list";
	}

	
}
