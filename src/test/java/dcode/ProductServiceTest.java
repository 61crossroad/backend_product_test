package dcode;

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
	ProductService service;
	
	@Test
	public void getProducts_returnList() {
		System.out.println("\n\n#ProductServiceTest#getProducts_returnList\n");
		
		List<ProductListResponse> list = service.getProducts();
		list.forEach(product -> {
			System.out.println(product.toString());
		});
	}
	
	@Test
	public void getProductDetail_returnObject() {
		System.out.println("\n\n#ProductServiceTest#getProductDetail_returnObject\n");
		
		ProductDetailResponse response = service.getProductDetail(9);
		System.out.println(response.toString());
	}
}
