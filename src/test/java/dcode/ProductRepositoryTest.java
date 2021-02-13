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
	private ProductRepository repository;
	
	/* @Test
	public void getProduct_returnDataSqlProduct() {
		Product product = repository.getProduct(1);
		System.out.println(product.getId() + ", " + product.getName() + ", " + product.getPrice());
	} */
	
	@Test
	public void getRepresentList() {
		System.out.println("\nProductRepositoryTest#getRepresentList");
		List<ProductComposition> list = repository.getProductCompositionRepresentList();
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	}
	
	@Test
	public void getCompositionList() {
		System.out.println("\nProductRepositoryTest#getCOmpositiontList");
		List<ProductComposition> list = repository.getProductCompositionList(11);
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	}
}
