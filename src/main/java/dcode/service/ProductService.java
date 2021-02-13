package dcode.service;

import dcode.domain.entity.ProductComposition;
import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public List<ProductListResponse> getProducts(){
        // System.out.println("상품 리스트 api를 완성 시켜주세요.");
        // return ProductListResponse.builder().id(1).build();
    	
    	List<ProductListResponse> response = new ArrayList<ProductListResponse>();
    	
    	List<ProductComposition> productCompositionList = repository.getProductCompositionRepresentList();
    	productCompositionList.forEach(pc -> {
    		response.add(pc.toListResponse().get());
    	});
    	
    	return response;
    }

    public ProductDetailResponse getProductDetail(int compId){
    	// System.out.println("상품 상세 api를 완성 시켜주세요.");
    	// return ProductDetailResponse.builder().id(1).build();
    	
    	ProductDetailResponse response = new ProductDetailResponse();
    	
    	List<ProductComposition> productCompositionList = repository.getProductCompositionList(compId);
    	
    	return response;
    }
}
