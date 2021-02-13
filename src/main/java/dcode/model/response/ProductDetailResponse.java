package dcode.model.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
public class ProductDetailResponse extends ProductResponse {
	List<SubProductResponse> subProducts;
	
	public ProductDetailResponse() {
		this.subProducts = new ArrayList<SubProductResponse>();
	}
}
