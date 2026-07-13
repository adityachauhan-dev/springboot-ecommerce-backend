package in.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductResponse {
	
	private int id;

	private String name;
	
	private String description;
	
	private double price;
	
	private Integer stock;
	
	private String categoryName;
	
	private String BrandName;
	
	private String imageUrl;
}