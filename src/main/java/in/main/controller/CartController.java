package in.main.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.request.CartRequest;
import in.main.dto.response.CartResponse;
import in.main.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "CART APIs", description = "Manage Cart")
public class CartController {

	private final CartService service;
	
	@Operation(summary = "POST Mapping",description = "Add Cart")
	@PostMapping
	public CartResponse addToCart(@Valid @RequestBody CartRequest request) {
		return service.addToCart(request);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Card By UserId")
	@GetMapping("/{userId}")
	public CartResponse getCart(@PathVariable int userId) {
		return service.getCard(userId);
	}
	
	@Operation(summary = "Delete Mapping",description = "Delete Cart By CardItemId")
	@DeleteMapping("/{cartItemId}")
	public String removeFromCart(@PathVariable int cartItemId) {
		service.removeFromCart(cartItemId);
		return "Item Remove Successfully";
	}
	
	@Operation(summary = "Delete Mapping",description = "Delete Card By UserId")
	@DeleteMapping("/{userId}")
	public String clearCart(@PathVariable int userId) {
		service.clearCart(userId);
		return "Cart Clear Successfully";
	}
	
}
