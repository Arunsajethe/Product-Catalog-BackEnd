package project.training.productcatalog.subproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Sub_ProductRequest {
	
	private String sname;
	
	private String desp;
	
	private String validity;

}
