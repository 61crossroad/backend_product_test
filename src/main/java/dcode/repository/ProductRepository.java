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
    
    public List<Product> getProductList() {
    	return this.getProductList(new ArrayList<>());
    }
    
    public List<Product> getProductList(List<Integer> ids) {
    	String query = "SELECT p.id, p.name, p.price, p.quantity, c.id AS cat_id, c.name AS cat_name FROM product p"
    			+ " LEFT OUTER JOIN category c ON c.id = p.cat_id"
    			+ (ids.isEmpty() ? "" : " WHERE p.id IN (:ids)");
    	
    	MapSqlParameterSource params = new MapSqlParameterSource("ids", ids);
    	
    	return namedParameterJdbcTemplate.query(
    			query,
    			params,
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
    
    public ProductType getProductType(int id) {
    	String query = "SELECT pt.id, pt.name, td.cat_id, td.attribute, td.discount FROM product_type pt"
    			+ " LEFT OUTER JOIN type_desc td ON td.type_id = pt.id"
    			+ " WHERE pt.id = :id";
    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        
        return namedParameterJdbcTemplate.query(
        		query,
        		params,
        		rs -> {
        			ProductType resultMap = ProductType.builder().typeDescList(new ArrayList<>()).build();
        			int resultId = 0;
        			String resultName = "";
        			
        			while (rs.next()) {
        				resultId = rs.getInt("id");
        				resultName = rs.getString("name");
        				TypeDesc result = TypeDesc.builder()
        						.catId(rs.getInt("cat_id"))
        						.attribute(Optional.ofNullable(rs.getString("attribute")).orElse("m"))
        						.discount(rs.getInt("discount"))
        						.build();
        				
        				resultMap.getTypeDescList().add(result);
        			}
        			
        			resultMap.setId(resultId);
        			resultMap.setName(resultName);
        			
        			return resultMap;
        		}
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
    					Integer resultId = rs.getInt("id");
    					
    					ProductType result = resultMap.getOrDefault(
    							resultId,
    							ProductType.builder()
    									.id(resultId)
    									.name(rs.getString("name"))
    									.typeDescList(new ArrayList<>())
    									.build()
    					);
    					
    					if (Optional.ofNullable(rs.getInt("cat_id")).isPresent()) {
    						result.getTypeDescList().add(
    								TypeDesc.builder()
    										.catId(rs.getInt("cat_id"))
    										.attribute(Optional.ofNullable(rs.getString("attribute")).orElse("m"))
    										.discount(rs.getInt("discount"))
    										.build()
    						);
    					}
    					
    					resultMap.put(resultId, result);
    				}
    				
    				return new ArrayList<ProductType>(resultMap.values());
    			}
    	);
    }
}
