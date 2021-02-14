package dcode.service;

import dcode.domain.entity.Product;
import dcode.domain.entity.ProductType;
import dcode.domain.entity.TypeDesc;
import dcode.model.queue.ProductQueue;
import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public List<ProductListResponse> getProducts(){
        List<Product> productList = repository.getProductList();
        List<ProductType> productTypeList = repository.getProductTypeList();
        
        List<ProductListResponse> productListResponseList = new ArrayList<ProductListResponse>();
        Queue<ProductQueue> queue = new LinkedList<>();
        
        productTypeList.forEach(productType -> {
        	List<TypeDesc> typeDescList = productType.getTypeDescList();
        	int firstCatId = typeDescList.get(0).getCatId();
        	
        	// init queue
        	queue.clear();
        	productList.forEach(product -> {
        		if (firstCatId == 0 || firstCatId == product.getCategory().getId()) {
        			String id = Integer.toString(productType.getId() * 10) + Integer.toString(product.getId() * 10);
        			
        			queue.add(ProductQueue.builder()
        					.id(id)
        					.name(product.getName())
        					.price(product.getPrice())
        					.quantity(product.getQuantity())
        					.depth(0)
        					.build()
        			);
        		}
        	});
        	
        	// traverse queue
        	while (!queue.isEmpty()) {
        		// pop
        		ProductQueue element = queue.poll();
        		int nextDepth = element.getDepth() + 1;
        		
        		if (nextDepth == typeDescList.size()) { // leaf node
        			productListResponseList.add(element.toListResponse(productType.getId(), productType.getName()));
        		} else {
        			TypeDesc nextTypeDesc = typeDescList.get(nextDepth);
        			
        			// push
        			productList.forEach(product -> {
        				if (product.getCategory().getId() == nextTypeDesc.getCatId()) {
        					int quantity = element.getQuantity();
        					if (productType.getId() != 4 && product.getQuantity() < quantity) {
        						quantity = product.getQuantity();
        					}
        					
        					queue.add(ProductQueue.builder()
        							.id(element.getId() + Integer.toString(product.getId() * 10))
        							.name(element.getName())
        							.price(element.getPrice())
        							.quantity(quantity)
        							.depth(nextDepth)
        							.build()
        					);
        				}
        			});
        		}
        	}
        });
        
        return productListResponseList;
    }

    public ProductDetailResponse getProductDetail(){
        System.out.println("상품 상세 api를 완성 시켜주세요.");
        return ProductDetailResponse.builder().id(1).build();
    }
}
