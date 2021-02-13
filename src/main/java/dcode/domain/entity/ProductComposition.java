package dcode.domain.entity;

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
}
