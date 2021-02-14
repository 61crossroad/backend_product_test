package dcode.controller;

import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.service.ProductService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ProductListResponse> getProducts() {
        ProductListResponse response = service.getProducts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //상품 상세 api
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable("productId") String productId) {
    	// parse productId
        ProductDetailResponse response = service.getProductDetail();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
