package in.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import in.main.dto.request.CartRequest;
import in.main.dto.response.CartItemResponse;
import in.main.dto.response.CartResponse;
import in.main.entity.Cart;
import in.main.entity.CartItem;
import in.main.entity.Product;
import in.main.entity.User;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.CartItemRepository;
import in.main.repository.CartRepository;
import in.main.repository.ProductRepository;
import in.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;

	@Override
	public CartResponse addToCart(CartRequest request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

		Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			newCart.setTotalPrice(0.0);
			return cartRepository.save(newCart);
		});
		double itemPrice = product.getPrice() * request.getQuantity();
		CartItem cartItem = CartItem.builder().cart(cart).product(product).quantity(request.getQuantity())
				.price(itemPrice).build();
		cartItemRepository.save(cartItem);

		cart.setTotalPrice(cart.getTotalPrice() + itemPrice);

		cartRepository.save(cart);

		return getCard(user.getId());
	}

	@Override
	public CartResponse getCard(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		Cart cart = cartRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		List<CartItemResponse> items = new ArrayList<CartItemResponse>();
		for (CartItem item : cartItems) {
			CartItemResponse response = CartItemResponse.builder().cartItemId(item.getId())
					.productId(item.getProduct().getId()).productName(item.getProduct().getName())
					.quantity(item.getQuantity()).price(item.getProduct().getPrice()).subTotal(item.getPrice()).build();
			items.add(response);

		}

		return CartResponse.builder().cartId(cart.getId()).totalPrice(cart.getTotalPrice()).items(items).build();
	}

	@Override
	public void removeFromCart(int cartItemId) {

		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found"));

		Cart cart = cartItem.getCart();
		cart.setTotalPrice(cart.getTotalPrice() - cartItem.getPrice());
		cartRepository.save(cart);
		cartItemRepository.delete(cartItem);

	}

	@Override
	public void clearCart(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		Cart cart = cartRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

		List<CartItem> items = cartItemRepository.findByCart(cart);
		cartItemRepository.deleteAll(items);
		cart.setTotalPrice(0.0);
		cartRepository.save(cart);
	}

}
