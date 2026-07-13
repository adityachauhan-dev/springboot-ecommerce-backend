package in.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.main.dto.request.PaymentRequest;
import in.main.dto.response.PaymentResponse;
import in.main.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name = "Payment APIs",description = "Manage Payment")
public class PaymentController {

	private final PaymentService service;
	
	@Operation(summary = "POST Mapping",description = "Make Payment")
	@PostMapping
	public PaymentResponse makePayment(@Valid @RequestBody PaymentRequest request) {
		return service.makePayment(request);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Payment By Id")
	@GetMapping("/{id}")
	public PaymentResponse getPaymentById(@PathVariable int id) {
		return service.getPaymentById(id);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Payment By OrderId")
	@GetMapping("/{orderId}")
	public PaymentResponse gePaymentByOrder(@PathVariable int orderId) {
		return service.getPaymentByOrder(orderId);
	}
	
}