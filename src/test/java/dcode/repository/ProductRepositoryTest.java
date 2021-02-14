package dcode.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.domain.entity.Product;
import dcode.domain.entity.ProductType;

@SpringBootTest
public class ProductRepositoryTest {
	@Autowired
	ProductRepository repository;
	
	/*
	@Test
	public void testGetProduct() {
		Product product = repository.getProduct(1);
		
		System.out.println(product.toString() + "\n\n");
	}
	*/
	
	@Test
	public void testGetProductList() {
		List<Product> list = repository.getProductList();
		list.forEach(product -> { System.out.println(product.toString()); });
		
		System.out.println("");
	}
	
	@Test
	public void testGetProductTypeList() {
		List<ProductType> list = repository.getProductTypeList();
		list.forEach(productType -> { System.out.println(productType.toString()); });
		
		System.out.println("");
	}
}
