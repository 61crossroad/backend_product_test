package dcode.service;

import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductListResponse getProducts(){
        System.out.println("상품 리스트 api를 완성 시켜주세요.");
        return ProductListResponse.builder().id(1).build();
    }

    public ProductDetailResponse getProductDetail(){
        System.out.println("상품 상세 api를 완성 시켜주세요.");
        return ProductDetailResponse.builder().id(1).build();
    }
}
