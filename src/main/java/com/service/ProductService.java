package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.entity.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	// Insertion
	public Product addSingle(Product p) {
		try {
			Product product = this.productDao.save(p);
			if (product == null) {
				return null;
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> addMultiple(List<Product> products) {
		try {
			List<Product> p = this.productDao.saveAll(products);
			if (p == null) {
				return null;
			}
			return p;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Deletion
	public boolean deleteSingle(int id) {
		try {
			Product p = this.findSingle(id);
			if (p != null) {
				System.out.println("Product is present:");
				this.productDao.delete(p);
				return true;
			}
			System.out.println("Product is not present");
			return false;

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Error occured");
			return false;
		}

	}

	public boolean deleteAll(List<Integer> ids) {
		try {
			this.productDao.deleteAllById(ids);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteAll() {
		try {
			this.productDao.deleteAll();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Select

	public Product findSingle(int id) {
		try {
			Optional<Product> p = this.productDao.findById(id);
			if (p.isPresent()) {
				return p.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public List<Product> findMultiple(List<Integer> id) {
		try {
			Iterable<Product> p = this.productDao.findAllById(id);
			return (List<Product>) p;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public List<Product> findAll() {
		try {
			Iterable<Product> i = this.productDao.findAll();
			if (i == null) {
				return null;
			}
			return (List<Product>) i;
		} catch (Exception e) {
			return null;
		}
	}

}
