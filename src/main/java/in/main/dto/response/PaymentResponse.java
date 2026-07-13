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
public class PaymentResponse {

	private int paymentId;
	
	private int orderId;
	
	private double amount;
	
	private String paymentMethod;
	
	private String paymentStatus;
}
