package in.main.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.request.AddressRequest;
import in.main.dto.response.AddressResponse;
import in.main.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Tag(
		name = "Address APIs",
		description = "Manage Address")
public class AddressController {
	
	AddressService service;
	
	@Operation(summary = "POST Mapping",description = "Create Address")
	@PostMapping
	public AddressResponse createAddress(@Valid @RequestBody AddressRequest request) {
		return service.createAddress(request);
	}
	
	@Operation(summary = "Get Mapping", description = "Fetch Address By User")
	@GetMapping("/{userId}")
	public List<AddressResponse> getAddressByUser(@PathVariable int userId){
		return service.getAddressByUser(userId);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Mapping", description = "Fetch Address By Id")
	public AddressResponse getAddressById(@PathVariable int id) {
		return service.getAddressById(id);
	}
	
	@Operation(summary = "Delete Mapping", description = "Delete Address By id")
	@DeleteMapping("/{id}")
	public String deleteAddress(@PathVariable int id) {
		service.deleteAddress(id);
		return "Delete Address SuccessFully";
	}
	
}