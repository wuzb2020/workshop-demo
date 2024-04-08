package com.educandoweb.course.controller;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Sku;
import com.educandoweb.course.entities.enums.PaymentType;
import com.educandoweb.course.services.OrderService;
import com.educandoweb.course.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/skus")
public class SkuController {
	
	@Autowired
	private SkuService service;

	@GetMapping(value = "/{id}") 
	public ResponseEntity<Sku> findById(@PathVariable Long id) {
		Sku obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * 新增sku
	 * @return
	 */
	@GetMapping(value = "/save")
	public ResponseEntity<Sku> save(@RequestBody Sku sku) {
		Sku skuSave = service.save(sku);
		return ResponseEntity.ok().body(skuSave);
	}

}
