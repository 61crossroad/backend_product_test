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
import java.util.Optional;
import java.util.Queue;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public List<ProductListResponse> getProducts(){
    	System.out.println("ProductService#getProducts");
    	
        List<Product> productList = repository.getProductList();
        List<ProductType> productTypeList = repository.getProductTypeList();
        
        List<ProductListResponse> productListResponseList = new ArrayList<>();
        Queue<Product> queue = new LinkedList<>();
        
        productTypeList.forEach(productType -> {
        	List<TypeDesc> typeDescList = productType.getTypeDescList();
        	
        	// init queue
        	queue.clear();
        	
        	int firstCatId = typeDescList.get(0).getCatId();
        	String firstAttr = typeDescList.get(0).getAttribute();
        	
        	// push initial products
        	productList.forEach(product -> {
        		if (firstCatId == 0 || firstCatId == product.getCategory().getId()) {
        			String uid = Integer.toString(productType.getId()) + "t" + Integer.toString(product.getId()) + firstAttr;
        			
        			queue.add(Product.builder()
        					.id(product.getId())
        					.uid(uid)
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
        		
        		if (nextDepth == typeDescList.size()) {
        			// add leaf element to the ResponseList
        			productListResponseList.add(
        					element.toListResponse(
        							productType,
        							typeDescList.get(nextDepth - 1))
        			);
        		} else {
        			// push products
        			TypeDesc nextTypeDesc = typeDescList.get(nextDepth);
        			
        			productList.forEach(product -> {
        				int nextCatId =nextTypeDesc.getCatId();
        				String nextAttr = nextTypeDesc.getAttribute();
        				if (nextCatId == 0 || nextCatId == product.getCategory().getId()) {
        					// handle quantity for sub & optional products
        					int quantity = element.getQuantity();
        					if (!"o".equals(nextTypeDesc.getAttribute()) // optional product attribute
        							&& product.getQuantity() < quantity) {
        						quantity = product.getQuantity();
        					}
        					// end handle quantity for sub & optional products
        					
        					queue.add(Product.builder()
        							.id(product.getId())
        							.uid(element.getUid() + Integer.toString(product.getId()) + nextAttr)
        							.name(element.getName())
        							.price(element.getPrice())
        							.quantity(quantity)
        							.depth(nextDepth)
        							.build()
        					);
        				}
        			});
        			// end push products
        		}
        	}
        	// end traverse queue for each ProductType
        });
        
        return productListResponseList;
    }

    public List<ProductDetailResponse> getProductDetail(String productId){
    	System.out.println("ProductService#getProductDetail");
    	
    	// parse productId
    	String[] idSplit = productId.split("[^0-9]");
    	int typeId = Integer.parseInt(Optional.ofNullable(idSplit[0]).orElse("0"));
    	List<Integer> ids = new ArrayList<>();
    	
    	for (int i = 1; i < idSplit.length; i++) {
    		ids.add(Integer.parseInt(idSplit[i]));
    	}
    	// end parse productId

        ProductType productType = repository.getProductType(typeId);
        List<Product> productList = repository.getProductList(ids);
        List<TypeDesc> typeDescList = productType.getTypeDescList();
        
        // get minimum quantity (except for optional products)
        int minQuantity = Integer.MAX_VALUE;
        for (Product product : productList) {
        	TypeDesc type = typeDescList.stream()
        			.filter(t -> (t.getCatId() == 0 || t.getCatId() == product.getCategory().getId()))
        			.findAny().orElse(TypeDesc.builder().build());
        	if (!"o".equals(type.getAttribute()) && product.getQuantity() < minQuantity) {
        		minQuantity = product.getQuantity();
        	}
        }
        // end get minimum quantity (except for optional products)
        
        List<ProductDetailResponse> productDetailResponseList = new ArrayList<>();
        
        for (TypeDesc typeDesc : typeDescList) {
        	// select Product by catId
        	int catId = typeDesc.getCatId();
        	Product product = productList.stream()
        			.filter(p -> (catId == 0 || catId == p.getCategory().getId()))
        			.findAny().orElse(Product.builder().id(0).name("상품 없음").build());
        	
    		productDetailResponseList.add(
    				product.toDetailResponse(
    						productType,
    						typeDesc,
    						minQuantity)
    		);
        }
        
        return productDetailResponseList;
    }
}
