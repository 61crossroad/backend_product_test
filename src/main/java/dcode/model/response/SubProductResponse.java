package dcode.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
public class SubProductResponse extends ProductResponse {
	private boolean optional;
	
	@Override
	public SubProductResponse get(String soldOut) {
		this.setStatus(0 < this.getQuantity() ? "" : soldOut);
    	
		return this;
	}

}
