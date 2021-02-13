package dcode.service;

import dcode.domain.entity.ProductComposition;
import dcode.model.factory.ProductResponseFactory;
import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.model.response.SubProductResponse;
import dcode.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductResponseFactory factory;

    public List<ProductListResponse> getProducts(){
        // System.out.println("상품 리스트 api를 완성 시켜주세요.");
        // return ProductListResponse.builder().id(1).build();
    	
    	List<ProductListResponse> response = new ArrayList<ProductListResponse>();
    	
    	List<ProductComposition> productCompositionList = repository.getProductCompositionRepresentList();
    	productCompositionList.forEach(pc -> {
    		response.add((ProductListResponse) factory.createProductResponse(pc, "list"));
    	});
    	
    	return response;
    }

    public ProductDetailResponse getProductDetail(int compId){
    	// System.out.println("상품 상세 api를 완성 시켜주세요.");
    	// return ProductDetailResponse.builder().id(1).build();
    	
    	ProductDetailResponse response = new ProductDetailResponse();
    	
    	List<ProductComposition> productCompositionList = repository.getProductCompositionList(compId);
    	
    	if (productCompositionList.size() < 1) {
    		return response;
    	}
    	
		// 대표상품은 항상 첫번째 row로 DB에 저장되어 있다고 가정합니다
		response = (ProductDetailResponse) factory.createProductResponse(productCompositionList.get(0), "detail");
		
		for (int i = 1; i < productCompositionList.size(); i++) {
			ProductComposition pc = productCompositionList.get(i);
			
			List<SubProductResponse> subList = response.getSubProducts();
			subList.add((SubProductResponse) factory.createProductResponse(pc, "sub"));
		}
    	
    	return response;
    }
}
