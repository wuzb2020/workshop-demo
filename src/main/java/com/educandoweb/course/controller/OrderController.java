package com.educandoweb.course.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educandoweb.course.dto.OrderPageDTO;
import com.educandoweb.course.entities.enums.PaymentType;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.impl.OrderServiceImpl;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;

	@GetMapping(value = "/{id}") 
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * 查询订单
	 * @param orderPageDTO
	 * @return
	 */
	@PostMapping(value = "/getPage")
	public ResponseEntity<IPage<Order>> getPage(@RequestBody @Validated OrderPageDTO orderPageDTO) {
		IPage<Order> orderIPage = service.gePage(orderPageDTO);
		return ResponseEntity.ok().body(orderIPage);
	}

	/**
	 * 用户下单
	 * @param skuId skuId
	 * @param type 支付类型
	 * @return
	 */
	@GetMapping(value = "/save")
	public ResponseEntity<Order> save(@RequestParam("skuId")Long skuId,@RequestParam ("paymentType") PaymentType type) {
		Order order = service.save(skuId, type);
		return ResponseEntity.ok().body(order);
	}

}
