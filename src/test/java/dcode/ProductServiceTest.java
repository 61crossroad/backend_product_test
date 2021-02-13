package dcode;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.model.response.ProductListResponse;
import dcode.service.ProductService;

@SpringBootTest
public class ProductServiceTest {
	@Autowired
	ProductService service;
	
	@Test
	public void getProducts_returnList() {
		List<ProductListResponse> list = service.getProducts();
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	}
}
