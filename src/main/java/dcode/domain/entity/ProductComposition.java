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
	private int minQuantity;
	
	public ProductListResponse toListResponse() {
		return ProductListResponse.builder()
				.id(this.compId)
				.name(this.product.getName())
				.price(this.getProduct().getPrice() - this.discount)
				.quantity(this.product.getQuantity())
				// .status(this.product.getQuantity() == 0 ? "sold out" : "")
				.typeId(this.compositionType.getTypeId())
				.typeName(this.compositionType.getTypeName())
				.optional(this.optional)
				.minQuantity(this.minQuantity)
				.build();
	}
}
