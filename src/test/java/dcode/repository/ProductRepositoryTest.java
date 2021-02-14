package dcode.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dcode.domain.entity.Product;
import dcode.domain.entity.ProductType;

@SpringBootTest
public class ProductRepositoryTest {
	@Autowired
	ProductRepository repository;
	
	@Test
	public void testGetProductListInParams() {
		List<Integer> ids = Arrays.stream(new Integer[] {2, 3, 4}).collect(Collectors.toList());
		
		List<Product> list = repository.getProductList(ids);
		
		list.forEach(product -> System.out.println(product.toString()));
		System.out.println();
	}
	
	@Test
	public void testGetProductType() {
		for (int i = 1; i <= 7; i++) {
			ProductType type = repository.getProductType(i);
			
			System.out.println(type.toString());
		}
		System.out.println();
	}
	
	@Test
	public void testGetProductTypeList() {
		List<ProductType> list = repository.getProductTypeList();
		
		list.forEach(productType -> System.out.println(productType.toString()));
		System.out.println();
	}
	
	@Test
	public void testGetProductList() {
		List<Product> list = repository.getProductList();
		
		list.forEach(product -> System.out.println(product.toString()));
		System.out.println();
	}
}
