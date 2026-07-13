package in.main.service;

import java.util.List;

import in.main.dto.request.AddressRequest;
import in.main.dto.response.AddressResponse;

public interface IAddressService {

	AddressResponse createAddress(AddressRequest request);
	
	List<AddressResponse> getAddressByUser(int userId);
	
	AddressResponse getAddressById(int id);
	
	void deleteAddress(int id);
	
}
