package dcode.repository;

import dcode.domain.entity.Category;
import dcode.domain.entity.Product;
import dcode.domain.entity.ProductType;
import dcode.domain.entity.TypeDesc;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product getProduct(int id) {
        String query = "SELECT * FROM `product` WHERE id = :id ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getInt("price"))
                        .build()
        );
    }
    
    public List<Product> getProductList() {
    	String query = "SELECT p.id, p.name, p.price, p.quantity, c.id AS cat_id, c.name AS cat_name FROM product p"
    			+ " LEFT OUTER JOIN category c ON c.id = p.cat_id";
    	
    	return namedParameterJdbcTemplate.query(
    			query,
    			(rs, rowNum) -> Product.builder()
    					.id(rs.getInt("id"))
    					.name(rs.getString("name"))
    					.price(rs.getInt("price"))
    					.quantity(rs.getInt("quantity"))
    					.category(
    							Category.builder()
    							.id(rs.getInt("cat_id"))
    							.name(Optional.ofNullable(rs.getString("cat_name")).orElse(""))
    							.build()
    					)
    					.build()
    				
    	);
    }
    
    public List<ProductType> getProductTypeList() {
    	String query = "SELECT pt.id, pt.name, td.cat_id, td.attribute, td.discount FROM product_type pt"
    			+ " LEFT OUTER JOIN type_desc td ON td.type_id = pt.id"
    			+ " ORDER BY pt.id, td.seq ASC";
    	
    	return namedParameterJdbcTemplate.query(
    			query,
    			rs -> {
    				Map<Integer, ProductType> resultMap = new LinkedHashMap<>();
    				
    				while (rs.next()) {
    					Integer id = rs.getInt("id");
    					
    					ProductType productType = resultMap.getOrDefault(
    							id,
    							ProductType.builder()
    								.id(id)
    								.name(rs.getString("name"))
    								.typeDescList(new ArrayList<TypeDesc>())
    								.build()
    					);
    					
    					if (Optional.ofNullable(rs.getInt("cat_id")).isPresent()) {
    						productType.getTypeDescList().add(
    								TypeDesc.builder()
    								.catId(rs.getInt("cat_id"))
    								.attribute(Optional.ofNullable(rs.getString("attribute")).orElse("M"))
    								.discount(rs.getDouble("discount"))
    								.build()
    						);
    					}
    					
    					resultMap.put(id, productType);
    				}
    				
    				return new ArrayList<ProductType>(resultMap.values());
    			}
    			
    	);
    }
}
