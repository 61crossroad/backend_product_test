package dcode.service;

import dcode.domain.entity.Product;
import dcode.domain.entity.ProductType;
import dcode.domain.entity.TypeDesc;
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
        
        List<ProductListResponse> productListResponseList = new ArrayList<>();
        Queue<Product> queue = new LinkedList<>();
        
        productTypeList.forEach(productType -> {
        	List<TypeDesc> typeDescList = productType.getTypeDescList();
        	int firstCatId = typeDescList.get(0).getCatId();
        	
        	// init queue
        	queue.clear();
        	productList.forEach(product -> {
        		if (firstCatId == 0 || firstCatId == product.getCategory().getId()) {
        			String id = Integer.toString(productType.getId() * 10) + Integer.toString(product.getId() * 10);
        			
        			queue.add(Product.builder()
        					.idStr(id)
        					.name(product.getName())
        					.price(product.getPrice())
        					.quantity(product.getQuantity())
        					.depth(0)
        					.build()
        			);
        		}
        	});
        	// end init queue
        	
        	// traverse queue for each ProductType
        	while (!queue.isEmpty()) {
        		Product element = queue.poll(); // pop product
        		int nextDepth = element.getDepth() + 1;
        		
        		if (nextDepth == typeDescList.size()) { // add leaf node to the ResponseList
        			productListResponseList.add(
        					element.toListResponse(productType, typeDescList.get(nextDepth - 1))
        			);
        		} else {
        			TypeDesc nextTypeDesc = typeDescList.get(nextDepth);
        			
        			// push product
        			productList.forEach(product -> {
        				int nextCatId =nextTypeDesc.getCatId();
        				if (nextCatId == 0 || nextCatId == product.getCategory().getId()) {
        					// handle quantity for sub & optional products
        					int quantity = element.getQuantity();
        					if (!"O".equals(nextTypeDesc.getAttribute()) && product.getQuantity() < quantity) {
        						quantity = product.getQuantity();
        					}
        					// end handle quantity for sub & optional products
        					
        					queue.add(Product.builder()
        							.idStr(element.getIdStr() + Integer.toString(product.getId() * 10))
        							.name(element.getName())
        							.price(element.getPrice())
        							.quantity(quantity)
        							.depth(nextDepth)
        							.build()
        					);
        				}
        			});
        			// end push
        		}
        	}
        	// end traverse queue for each ProductType
        });
        
        return productListResponseList;
    }

    public List<ProductDetailResponse> getProductDetail(String productId){
        // parse productId
        int typeId = 2;
        List<Integer> ids = new ArrayList<>(); ids.add(3); ids.add(10);
        // end parse productId
        
        ProductType productType = repository.getProductType(typeId);
        List<Product> productList = repository.getProductList(ids);
        
        List<ProductDetailResponse> productDetailResponseList = new ArrayList<>();
        
        productType.getTypeDescList().forEach(typeDesc -> {
        	// select Product by catId
        	Product product = productList.stream()
        		.filter(p -> p.getCategory().getId() == typeDesc.getCatId())
        		.findAny().orElse(Product.builder().id(0).name("상품 없음").build());
        	
    		productDetailResponseList.add(product.toDetailResponse(productType, typeDesc));
        });
        
        return productDetailResponseList;
    }
}
