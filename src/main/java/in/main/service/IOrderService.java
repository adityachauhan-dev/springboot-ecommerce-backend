package in.main.service;

import java.util.List;

import in.main.dto.request.OrderRequest;
import in.main.dto.response.OrderResponse;
import in.main.enums.OrderStatus;

public interface IOrderService {

	OrderResponse placeOrder(OrderRequest request);
	
	List<OrderResponse> getOrderByUser(int userId);
	
	OrderResponse getOrderById(int orderId);
	
	OrderResponse updateOrderStatus(int orderId,OrderStatus status);
	
}