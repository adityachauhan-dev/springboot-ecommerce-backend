package in.main.service;

import org.springframework.stereotype.Service;

import in.main.dto.request.PaymentRequest;
import in.main.dto.response.PaymentResponse;
import in.main.entity.Order;
import in.main.entity.Payment;
import in.main.enums.PaymentStatus;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.OrderRepository;
import in.main.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

	private final PaymentRepository repository;
	private final OrderRepository orderRepository;

	@Override
	public PaymentResponse makePayment(PaymentRequest request) {
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));

		Payment payment = Payment.builder().amount(order.getTotalAmount()).paymentMethod(request.getPaymentMethod())
				.paymentStatus(PaymentStatus.SUCCESS).order(order).build();
		payment = repository.save(payment);

		return PaymentResponse.builder().paymentId(payment.getId()).orderId(order.getId()).amount(payment.getAmount())
				.paymentMethod(payment.getPaymentMethod().name()).paymentStatus(payment.getPaymentStatus().name())
				.build();
	}

	@Override
	public PaymentResponse getPaymentById(int paymentId) {
		Payment payment = repository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment Not Found"));

		return PaymentResponse.builder().paymentId(payment.getId()).orderId(payment.getOrder().getId())
				.amount(payment.getAmount()).paymentMethod(payment.getPaymentMethod().name())
				.paymentStatus(payment.getPaymentStatus().name()).build();
	}

	@Override
	public PaymentResponse getPaymentByOrder(int orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
		Payment payment = repository.findByOrder(order)
				.orElseThrow(() -> new ResourceNotFoundException("Payment Not Found"));

		return PaymentResponse.builder().paymentId(payment.getId()).orderId(order.getId()).amount(payment.getAmount())
				.paymentMethod(payment.getPaymentMethod().name()).paymentStatus(payment.getPaymentStatus().name())
				.build();
	}

}
