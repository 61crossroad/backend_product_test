package dcode.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListResponse {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int minQuantity;
    @Builder.Default
    private String status = "sold out";
    private int typeId;
    private String typeName;
    private boolean optional;
    
    public ProductListResponse get() {
    	if (typeId == 2) { // 1+1상품
    		this.setStatus(1 < this.quantity ? "" : this.status);
    	} else if (typeId == 3) { // 묶음상품
    		this.setStatus(0 < this.minQuantity ? "" : this.status);
    	} else { // 단일상품, 옵션 상품
    		this.setStatus(0 < this.quantity ? "" : this.status);
    	}
    	
    	return this;
    }
}
