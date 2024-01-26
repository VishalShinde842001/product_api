package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Product;
import com.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/add/single")
	public ResponseEntity<Product> addSingle(@RequestBody Product product) {
		Product p = this.productService.addSingle(product);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
	}

	@PostMapping("/add/multiple")
	public ResponseEntity<List<Product>> addMultiple(@RequestBody List<Product> products) {
		List<Product> productList = this.productService.addMultiple(products);
		if (productList == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Product product = this.productService.findSingle(id);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/get/multiple/{ids}")
	public ResponseEntity<List<Product>> getMultipleProducts(@PathVariable List<Integer> ids) {
		List<Product> productList = this.productService.findMultiple(ids);
		if (productList == null || productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = this.productService.findAll();
		if (productList == null || productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/single/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
		boolean deleted = this.productService.deleteSingle(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/multiple/{ids}")
	public ResponseEntity<Void> deleteMultipleProducts(@PathVariable List<Integer> ids) {
		boolean deleted = this.productService.deleteAll(ids);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/all")
	public ResponseEntity<Void> deleteAll() {
		boolean deleted = this.productService.deleteAll();
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
