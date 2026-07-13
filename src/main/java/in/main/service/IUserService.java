package in.main.service;

import java.util.List;

import in.main.dto.request.RegisterRequest;
import in.main.dto.response.UserResponse;

public interface IUserService {

	UserResponse getUserById(int id);
//	UserResponse getUserByEmail(String email);
	
	List<UserResponse> getAllUser();
	
	UserResponse register(RegisterRequest request);
	
	void deleteUser(int id);
	
}