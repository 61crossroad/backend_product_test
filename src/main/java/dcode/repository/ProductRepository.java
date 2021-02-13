package dcode.repository;

import dcode.domain.entity.CompositionType;
import dcode.domain.entity.Product;
import dcode.domain.entity.ProductComposition;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    
    public List<ProductComposition> getProductCompositionList(int compId) {
    	String query =
    			"SELECT pc.comp_id, pc.represent, pc.optional, pc.discount, ct.type_id, ct.type_name"
    			+ ", p.id, p.name, p.price, p.quantity, p.category"
    			+ ", (SELECT MIN(p_min.quantity) FROM product_composition pc_min"
    			+ " INNER JOIN product p_min ON p_min.id = pc_min.product_id"
    			+ " WHERE pc_min.comp_id = pc.comp_id) AS min_quantity"
    			+ " FROM product_composition pc"
    			+ " INNER JOIN composition_type ct ON ct.type_id = pc.type_id"
    			+ " INNER JOIN product p ON p.id = pc.product_id"
    			+ " WHERE pc.comp_id = :compId";
    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	params.addValue("compId", compId);
    	
    	return namedParameterJdbcTemplate.query(
    			query,
    			params,
    			(rs, rowNum) -> ProductComposition.builder()
    					.compId(rs.getInt("comp_id"))
    					.compositionType(
    							CompositionType.builder()
    							.typeId(rs.getInt("type_id"))
    							.typeName(rs.getString("type_name"))
    							.build()
    					)
    					.product(
    							Product.builder()
    							.id(rs.getInt("id"))
    							.name(rs.getString("name"))
    							.price(rs.getInt("price"))
    							.quantity(rs.getInt("quantity"))
    							.category(rs.getString("category"))
    							.build()
    					)
    					.represent(rs.getBoolean("represent"))
    					.optional(rs.getBoolean("optional"))
    					.discount(rs.getInt("discount"))
    					.minQuantity(rs.getInt("min_quantity"))
    					.build()
    	);
    }
    
    public List<ProductComposition> getProductCompositionRepresentList() {
    	String query = 
    			"SELECT pc.comp_id, pc.discount, ct.type_id, ct.type_name, p.name, p.price, p.quantity"
    			+ ", (SELECT MIN(p_min.quantity) FROM product_composition pc_min"
    			+ " INNER JOIN product p_min ON p_min.id = pc_min.product_id"
    			+ " WHERE pc_min.comp_id = pc.comp_id) AS min_quantity"
    			+ " FROM product_composition pc"
    			+ " INNER JOIN composition_type ct ON ct.type_id = pc.type_id"
    			+ " INNER JOIN product p ON p.id = pc.product_id"
    			+ " WHERE pc.represent = true";
    	
    	return namedParameterJdbcTemplate.query(
    			query,
    			(rs, rowNum) -> ProductComposition.builder()
    					.compId(rs.getInt("comp_id"))
    					.compositionType(
    							CompositionType.builder()
    							.typeId(rs.getInt("type_id"))
    							.typeName(rs.getString("type_name"))
    							.build()
    					)
    					.product(
    							Product.builder()
    							.name(rs.getString("name"))
    							.price(rs.getInt("price"))
    							.quantity(rs.getInt("quantity"))
    							.build()
    					)
    					.discount(rs.getInt("discount"))
    					.minQuantity(rs.getInt("min_quantity"))
    					.build()
    	);
    }
}
