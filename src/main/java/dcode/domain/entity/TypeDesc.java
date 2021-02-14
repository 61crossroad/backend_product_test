package dcode.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeDesc {
	private int catId;
	private String attribute;
	private double discount;
}
