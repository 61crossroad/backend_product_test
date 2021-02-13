package dcode.model.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDetailResponse {
    private int id;
    private String name;
    private int price;
    List<ProductListResponse> subProducts;
}
