package dcode.model.queue;

import dcode.model.response.ProductListResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductQueue {
	private String id;
	private String name;
	private int price;
	private int quantity;
	private String attribute;
	private int discount;
	private int depth;
	
	public ProductListResponse toListResponse(int typeId, String typeName) {
		String status = "";
		
		if (typeId == 2) {
			status = this.quantity < 2 ? "sold out" : status;
		} else {
			status = 0 < this.quantity ? "" : "sold out";
		}
		
		return ProductListResponse.builder()
				.id(Integer.parseInt(this.id))
				.name(this.name)
				.price(this.price)
				.status(status)
				.typeName(typeName)
				.build();
				
	}
}
