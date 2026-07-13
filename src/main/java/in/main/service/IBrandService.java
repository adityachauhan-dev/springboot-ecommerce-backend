package in.main.service;

import java.util.List;

import in.main.dto.request.BrandRequest;
import in.main.dto.response.BrandResponse;

public interface IBrandService {

	BrandResponse createBrand(BrandRequest request);
	
	List<BrandResponse> getAllBrand();
	
	BrandResponse getBrandById(int id);
	
	void deleteById(int id);
	
}
