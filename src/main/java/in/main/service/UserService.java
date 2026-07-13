package in.main.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import in.main.dto.request.RegisterRequest;
import in.main.dto.response.UserResponse;
import in.main.entity.User;
import in.main.enums.Role;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserResponse getUserById(int id) {

		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
				.phoneNumber(user.getPhoneNumber()).role(user.getRole().name()).build();
	}
	/*
	 * (OR)
	 * 
	 * @Override public UserResponse getUserByEmail(String email) { User user =
	 * repository.findByEmail(email).orElseThrow(() -> new
	 * RuntimeException("User Not Found")); return UserResponse.builder()
	 * .id(user.getId()) .name(user.getName()) .email(user.getEmail())
	 * .phoneNumber(user.getPhoneNumber()) .role(user.getRole().name()) .build(); }
	 */

	@Override
	public UserResponse register(RegisterRequest request) {
		if (repository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exist");
		}
		User user = User.builder().name(request.getName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).phoneNumber(request.getPhoneNumber())
				.role(Role.USER).build();

		user = repository.save(user);

		return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
				.phoneNumber(user.getPhoneNumber()).role(user.getRole().name()).build();
	}

	@Override
	public List<UserResponse> getAllUser() {

		List<User> users = repository.findAll();
		List<UserResponse> responses = new ArrayList<UserResponse>();
		for (User user : users) {
			UserResponse response = UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
					.phoneNumber(user.getPhoneNumber()).role(user.getRole().name()).build();
			responses.add(response);
		}
		return responses;
	}

	@Override
	public void deleteUser(int id) {

		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		repository.delete(user);
	}
}
