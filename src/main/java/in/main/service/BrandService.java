package in.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.main.controller.UserController;
import in.main.dto.request.BrandRequest;
import in.main.dto.response.BrandResponse;
import in.main.entity.Brand;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.BrandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

	private final BrandRepository repository;

	@Override
	public BrandResponse createBrand(BrandRequest request) {
		if (repository.existsByName(request.getName())) {
			throw new RuntimeException("Brand All Ready Exist");
		}
		Brand brand = Brand.builder().name(request.getName()).description(request.getDescription()).build();
		brand = repository.save(brand);
		
		return BrandResponse.builder().id(brand.getId()).name(brand.getName()).description(brand.getDescription())
				.build();
	}

	@Override
	public List<BrandResponse> getAllBrand() {
		List<Brand> brands = repository.findAll();
		List<BrandResponse> responses = new ArrayList<BrandResponse>();
		for (Brand brand : brands) {
			BrandResponse response = BrandResponse.builder().id(brand.getId()).name(brand.getName())
					.description(brand.getDescription()).build();
			responses.add(response);
		}
		return responses;
	}

	@Override
	public BrandResponse getBrandById(int id) {
		Brand brand = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not found"));
		
		return BrandResponse.builder().id(brand.getId()).name(brand.getName()).description(brand.getDescription())
				.build();
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
