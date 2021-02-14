package dcode.domain.entity;

import dcode.model.response.ProductDetailResponse;
import dcode.model.response.ProductListResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private Category category;
    
    private String uid;
    
    // fields for queue traverse
    private String attribute;
	private int discount;
	private int depth;
	
	// handle quantity status
	private String getStatus(String attribute) {
		return this.getStatus(attribute, this.quantity);
	}
	
	private String getStatus(String attribute, int quantity) {
		if ("p".equals(attribute)) { // 1+1 attribute
			return quantity < 2 ? "sold out" : "";
		} else {
			return 0 < quantity ? "" : "sold out";
		}
	}
	// end handle quantity status
	
	// create detail response object
	public ProductDetailResponse toDetailResponse(ProductType type, TypeDesc desc, int minQuantity) {
		// handle quantity status
		int quantity;
		if (!"o".equals(desc.getAttribute())) { // optional product attribute
			quantity = minQuantity;
		} else {
			quantity = this.quantity;
		}
		// end handle quantity status
		
		return ProductDetailResponse.builder()
				.id(this.id)
				.name(this.name)
				.price(this.price * (100 - desc.getDiscount()) / 100)
				.status(this.getStatus(desc.getAttribute(), quantity))
				.typeName(type.getName())
				.attribute(desc.getAttribute())
				.build();
	}
	
	// create list response object
	public ProductListResponse toListResponse(ProductType type, TypeDesc desc) {
		return ProductListResponse.builder()
				.id(this.id)
				.uid(this.uid)
				.name(this.name)
				.price(this.price)
				.status(this.getStatus(desc.getAttribute()))
				.typeName(type.getName())
				.build();
	}
}
