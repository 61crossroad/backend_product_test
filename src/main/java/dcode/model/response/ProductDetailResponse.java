package dcode.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponse {
    private int id;
    private String name;
    private int price;
    private String status;
    private String typeName;
    private String attribute;
}
