package in.main.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

	@NotBlank
	private String name;
	
	private String description;
	
	@NotNull
	@Positive
	private double price;
	
	@NotNull
	private Integer stock;
	
	@NotNull
	private Integer categoryId;
	
	@NotNull
	private Integer BrandId;
	
	@NotNull
	private MultipartFile image;
	
}