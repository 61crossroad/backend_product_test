package dcode.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListResponse {
    private int id;
    private String name;
    private int price;
    private String uid;
    private String status;
    private String typeName;
}
