package dcode.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.domain.entity.Product;

@SpringBootTest
public class ProductRepositoryTest {
	@Autowired
	ProductRepository repository;
	
	@Test
	public void testGetProduct() {
		Product product = repository.getProduct(1);
		System.out.println("## testGetProduct\n-- " + product.toString());
	}
}
