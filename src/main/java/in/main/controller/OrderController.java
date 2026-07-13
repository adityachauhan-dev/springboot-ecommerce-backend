package in.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.main.dto.request.OrderRequest;
import in.main.dto.response.OrderResponse;
import in.main.enums.OrderStatus;
import in.main.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order APIs",description = "Manage Order")
public class OrderController {

	private final OrderService service;

 
	@Operation(summary = "POST Mapping",description = "Place Order")
	@PostMapping
	public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
		return service.placeOrder(request);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Order By Id")
	@GetMapping("/{id}")
	public OrderResponse getOrderById(@PathVariable int id) {
		return service.getOrderById(id);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Order By UserId")
	@GetMapping("/{userId}")
	public List<OrderResponse> getOrderByUser(@PathVariable int userId){
		return service.getOrderByUser(userId);
	}
	
	@PutMapping("/{orderId}")
	public OrderResponse updateOrderStatus(@PathVariable int orderId,@RequestParam OrderStatus status) {
		return service.updateOrderStatus(orderId, status);
	}

}