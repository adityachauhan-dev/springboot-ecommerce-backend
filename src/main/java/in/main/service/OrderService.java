package in.main.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import in.main.dto.request.OrderRequest;
import in.main.dto.response.OrderItemResponse;
import in.main.dto.response.OrderResponse;
import in.main.entity.Address;
import in.main.entity.Cart;
import in.main.entity.CartItem;
import in.main.entity.Order;
import in.main.entity.OrderItem;
import in.main.entity.User;
import in.main.enums.OrderStatus;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.AddressRepository;
import in.main.repository.CartItemRepository;
import in.main.repository.CartRepository;
import in.main.repository.OrderItemRepository;
import in.main.repository.OrderRepository;
import in.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	public OrderResponse placeOrder(OrderRequest request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		Address address = addressRepository.findById(request.getAddressId())
				.orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		if (cartItems.isEmpty()) {
			throw new RuntimeException("Cart is Empty");
		}
		Order order = Order.builder().orderDate(LocalDateTime.now()).totalAmount(cart.getTotalPrice())
				.status(OrderStatus.PENDING).user(user).address(address).build();
		order = orderRepository.save(order);
		List<OrderItemResponse> itemResponses = new ArrayList<OrderItemResponse>();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = OrderItem.builder().order(order).product(cartItem.getProduct())
					.quantity(cartItem.getQuantity()).price(cartItem.getPrice()).build();
			orderItemRepository.save(orderItem);

			itemResponses.add(OrderItemResponse.builder().productName(cartItem.getProduct().getName())
					.quantity(cartItem.getQuantity()).price(cartItem.getPrice()).build());
		}
		cartItemRepository.deleteAll(cartItems);
		cart.setTotalPrice(0.0);
		cartRepository.save(cart);

		return OrderResponse.builder().orderId(order.getId()).orderDate(order.getOrderDate())
				.totalAmount(order.getTotalAmount()).status(order.getStatus().name()).items(itemResponses).build();

	}

	@Override
	public List<OrderResponse> getOrderByUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		List<Order> orders = orderRepository.findByUser(user);
		List<OrderResponse> responses = new ArrayList<OrderResponse>();
		for (Order order : orders) {
			List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
			List<OrderItemResponse> itemResponses = new ArrayList<OrderItemResponse>();

			for (OrderItem item : orderItems) {
				itemResponses.add(OrderItemResponse.builder().productName(item.getProduct().getName())
						.quantity(item.getQuantity()).price(item.getPrice()).build());
			}
			responses.add(OrderResponse.builder().orderId(order.getId()).orderDate(order.getOrderDate())
					.totalAmount(order.getTotalAmount()).items(itemResponses).build());
		}
		return responses;
	}

	@Override
	public OrderResponse getOrderById(int orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));

		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		List<OrderItemResponse> itemResponses = new ArrayList<OrderItemResponse>();
		for (OrderItem item : orderItems) {
			itemResponses.add(OrderItemResponse.builder().productName(item.getProduct().getName())
					.quantity(item.getQuantity()).price(item.getPrice()).build());
		}
		return OrderResponse.builder().orderId(order.getId()).orderDate(order.getOrderDate())
				.totalAmount(order.getTotalAmount()).status(order.getStatus().name()).items(itemResponses).build();
	}

	@Override
	public OrderResponse updateOrderStatus(int orderId, OrderStatus status) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
		order.setStatus(status);

		order = orderRepository.save(order);
		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		List<OrderItemResponse> itemResponses = new ArrayList<OrderItemResponse>();

		for (OrderItem item : orderItems) {
			itemResponses.add(OrderItemResponse.builder().productName(item.getProduct().getName())
					.quantity(item.getQuantity()).price(item.getPrice()).build());
		}
		return OrderResponse.builder().orderId(order.getId()).orderDate(order.getOrderDate())
				.totalAmount(order.getTotalAmount()).status(order.getStatus().name()).items(itemResponses).build();
	}

}
