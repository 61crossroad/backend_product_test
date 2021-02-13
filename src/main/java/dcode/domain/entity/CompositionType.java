package dcode.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompositionType {
	private int typeId;
	private String typeName;
}
