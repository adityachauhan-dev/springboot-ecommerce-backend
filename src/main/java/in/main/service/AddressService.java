package in.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import in.main.dto.request.AddressRequest;
import in.main.dto.response.AddressResponse;
import in.main.entity.Address;
import in.main.entity.User;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.AddressRepository;
import in.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

	private final AddressRepository repository;
	private final UserRepository userRepository;

	@Override
	public AddressResponse createAddress(AddressRequest request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		Address address = Address.builder().fullName(request.getFullName()).phoneNumber(request.getPhoneNumber())
				.street(request.getStreet()).city(request.getCity()).state(request.getState())
				.pincode(request.getPincode()).user(user).build();
		address = repository.save(address);

		return AddressResponse.builder().id(address.getId()).fullName(address.getFullName())
				.phoneNumber(address.getPhoneNumber()).street(address.getStreet()).city(address.getCity())
				.state(address.getState()).pincode(address.getPincode()).userId(user.getId()).build();
	}

	@Override
	public List<AddressResponse> getAddressByUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		List<Address> addresses = repository.findByUser(user);
		List<AddressResponse> responses = new ArrayList<AddressResponse>();
		for (Address address : addresses) {
			responses.add(AddressResponse.builder().id(address.getId()).fullName(address.getFullName())
					.phoneNumber(address.getPhoneNumber()).street(address.getStreet()).city(address.getCity())
					.state(address.getState()).pincode(address.getPincode()).userId(user.getId()).build());
		}

		return responses;
	}

	@Override
	public AddressResponse getAddressById(int id) {
		Address address = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));

		return AddressResponse.builder().id(address.getId()).fullName(address.getFullName())
				.phoneNumber(address.getPhoneNumber()).street(address.getStreet()).city(address.getCity())
				.state(address.getState()).pincode(address.getPincode()).userId(address.getUser().getId()).build();
	}

	@Override
	public void deleteAddress(int id) {
		repository.deleteById(id);
	}

}
