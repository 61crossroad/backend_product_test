package dcode.model.factory;

import org.springframework.stereotype.Component;

import dcode.domain.entity.ProductComposition;
import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import dcode.model.response.ProductResponse;
import dcode.model.response.SubProductResponse;

@Component
public class ProductResponseFactory {
	public ProductResponse createProductResponse(ProductComposition productComposition, String responseType) {
		String param = "sold out";
		
		if ("detail".equals(responseType)) {
			ProductDetailResponse productResponse = new ProductDetailResponse();
			setProductResponseFields(productComposition, productResponse);
			
			return productResponse.get(param);
		} else if("sub".equals(responseType)) { 
			SubProductResponse productResponse = new SubProductResponse();
			setProductResponseFields(productComposition, productResponse);
			
			productResponse.setOptional(productComposition.isOptional());
			
			return productResponse.get(param);
		} else if ("list".equals(responseType)) {
			ProductListResponse productResponse = new ProductListResponse();
			setProductResponseFields(productComposition, productResponse);
			
			return productResponse.get(param);
		} else {
			return new ProductListResponse();
		}
	}
	
	private void setProductResponseFields(ProductComposition pc, ProductResponse pr) {
		pr.setId(pc.getCompId());
		pr.setName(pc.getProduct().getName());
		pr.setPrice(pc.getProduct().getPrice() - pc.getDiscount());
		pr.setQuantity(pc.getProduct().getQuantity());
		pr.setMinQuantity(pc.getMinQuantity());
		pr.setTypeId(pc.getCompositionType().getTypeId());
		pr.setTypeName(pc.getCompositionType().getTypeName());
	}
}
