package dcode.domain.entity;

import dcode.model.response.ProductListResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductComposition {
	private int compId;
	private CompositionType compositionType;
	private Product product;
	private boolean represent;
	private boolean optional;
	private int discount;
	
	public ProductListResponse toListResponse() {
		return ProductListResponse.builder()
				.id(this.compId)
				.name(this.product.getName())
				.price(this.getProduct().getPrice() - this.discount)
				.productCount(this.product.getCount())
				.productStatus(this.product.getCount() == 0 ? "sold out" : "")
				.typeName(this.compositionType.getTypeName())
				.build();
	}
}
