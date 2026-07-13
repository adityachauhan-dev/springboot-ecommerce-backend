package in.main.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class BrandRequest {

	@NotBlank(message = "Brand name is Required")
	@Size(min = 2, max = 50)
	private String name;
	
	@Size(max = 250)
	private String description;
	
}
