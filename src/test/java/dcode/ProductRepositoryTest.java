package dcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.domain.entity.Product;
import dcode.repository.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void getProduct_returnDataSqlProduct() {
		Product product = productRepository.getProduct(1);
		System.out.println(product.getId() + ", " + product.getName() + ", " + product.getPrice());
	}
}
