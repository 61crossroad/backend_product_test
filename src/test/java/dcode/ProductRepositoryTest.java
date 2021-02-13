package dcode;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.domain.entity.Product;
import dcode.domain.entity.ProductComposition;
import dcode.repository.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepository;
	
	/* @Test
	public void getProduct_returnDataSqlProduct() {
		Product product = productRepository.getProduct(1);
		System.out.println(product.getId() + ", " + product.getName() + ", " + product.getPrice());
	} */
	
	/* @Test
	public void getRepresentList() {
		List<ProductComposition> list = productRepository.getProductCompositionRepresentList();
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	} */
	
	@Test
	public void getCompositionList() {
		List<ProductComposition> list = productRepository.getProductCompositionList(11);
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	}
}
