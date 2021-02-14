package dcode.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.service.ProductService;

@SpringBootTest
public class ProductServiceTest {
	@Autowired
	private ProductService service;
	
	@Test
	public void testGetProductDetail() {
		List<ProductDetailResponse> list = service.getProductDetail("3t5m9o");
		
		list.forEach(product -> System.out.println(product.toString()));
		System.out.println();
	}
	
	/*
	@Test
	public void testGetProducts() {
		List<ProductListResponse> list = service.getProducts();
		
		list.forEach(product -> System.out.println(product.toString()));
		System.out.println();
	}
	*/
}
