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
    
    // fields for queue traverse
    private String idStr;
    private String attribute;
	private int discount;
	private int depth;
	// end fields for queue traverse
	
	// handle quantity status
	private String getStatus(String attribute) {
		if ("P".equals(attribute)) {
			return this.quantity < 2 ? "sold out" : "";
		} else {
			return 0 < this.quantity ? "" : "sold out";
		}
	}
	
	// create detail response object
	public ProductDetailResponse toDetailResponse(ProductType type, TypeDesc desc) {
		return ProductDetailResponse.builder()
				.id(this.id)
				.name(this.name)
				.price(this.price * (100 - desc.getDiscount()) / 100)
				.status(this.getStatus(desc.getAttribute()))
				.typeName(type.getName())
				.attribute(desc.getAttribute())
				.build();
	}
	
	// create list response object
	public ProductListResponse toListResponse(ProductType type, TypeDesc desc) {
		return ProductListResponse.builder()
				.id(Integer.parseInt(this.idStr))
				.name(this.name)
				.price(this.price)
				.status(this.getStatus(desc.getAttribute()))
				.typeName(type.getName())
				.build();
	}
}
