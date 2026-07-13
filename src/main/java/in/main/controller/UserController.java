package in.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.request.RegisterRequest;
import in.main.dto.response.UserResponse;
import in.main.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "USER APIs",description = "Manage Users")
public class UserController {

	
	private final UserService service;
	
	@Operation(summary = "POST Mapping",description = "Register User")
	@PostMapping("/register")
	public UserResponse register(@Valid @RequestBody RegisterRequest request) {
		return service.register(request);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch User By Id")
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	@Operation(summary = "Get Mapping",description = "Fetch All Users")
	@GetMapping
	public List<UserResponse> getAllUsers(){
		return service.getAllUser();
	}
	
	@Operation(summary = "DELETE Mapping",description = "Delete User By Id")
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return "User Delete Successfully";
	}
}