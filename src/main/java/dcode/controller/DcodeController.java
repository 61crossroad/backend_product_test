package dcode.controller;

import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dcode")
public class DcodeController {

    private final ProductService service;

    //상품 리스트 api
    @GetMapping("/products")
    public ResponseEntity<List<ProductListResponse>> getProducts() {
        List<ProductListResponse> response = service.getProducts();
        
        HttpStatus httpStatus = 0 < response.size() ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(response, httpStatus);
    }

    //상품 상세 api
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable("productId") int productId) {
    	ProductDetailResponse response = service.getProductDetail(productId);
    		
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
