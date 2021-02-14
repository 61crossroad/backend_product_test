package dcode.domain.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductType {
	private int id;
	private String name;
	List<TypeDesc> typeDescList;
}
