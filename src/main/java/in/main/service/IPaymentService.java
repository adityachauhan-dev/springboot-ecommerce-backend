package in.main.service;

import in.main.dto.request.PaymentRequest;
import in.main.dto.response.PaymentResponse;

public interface IPaymentService {
	
	PaymentResponse makePayment(PaymentRequest request);
	
	PaymentResponse getPaymentById(int paymentId);
	
	PaymentResponse getPaymentByOrder(int orderId);
	
}