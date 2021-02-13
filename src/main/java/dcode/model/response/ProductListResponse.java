package dcode.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListResponse {
    private int id;
    private String name;
    private int price;
    private int productCount;
    private String productStatus;
    private String typeName;
    
}
