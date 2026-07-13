package in.main.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import in.main.enums.OrderStatus;
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
public class OrderResponse {

	private int orderId;
	
	private LocalDateTime orderDate;
	
	private double totalAmount;
	
	private String status;
	
	private List<OrderItemResponse> items;
	
}
