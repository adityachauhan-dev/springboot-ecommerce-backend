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
public class CartItemResponse {

	private int cartItemId;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	private double subTotal;
}
