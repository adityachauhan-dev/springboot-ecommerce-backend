package in.main.service;

import in.main.dto.request.CartRequest;
import in.main.dto.response.CartResponse;

public interface ICartService {

	CartResponse addToCart(CartRequest request);
	
	CartResponse getCard(int userId);
	
	void removeFromCart(int cartItemId);
	
	void clearCart(int userId);
	
}