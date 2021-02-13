package dcode.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductResponse {
	private int id;
    private String name;
    private int price;
    private int quantity;
    private int minQuantity;
    private String status;
    private int typeId;
    private String typeName;
    
    public ProductResponse get(String param) {
    	if (getTypeId() == 2) { // 1+1상품
    		this.setStatus(1 < this.getQuantity() ? "" : param);
    	} else if (getTypeId() == 3) { // 묶음상품
    		this.setStatus(0 < this.minQuantity ? "" : param);
    	} else { // 단일상품, 옵션 상품
    		this.setStatus(0 < this.getQuantity() ? "" : param);
    	}
    	
    	return this;
    }
}
