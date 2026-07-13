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

import in.main.dto.request.BrandRequest;
import in.main.dto.response.BrandResponse;
import in.main.repository.UserRepository;
import in.main.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brand APIs", description = "Manage Brand")
public class BrandController {

    private final UserRepository userRepository;

	private final BrandService service;

	
	@Operation(summary = "POST Mapping", description = "Create Brand")
    @PostMapping
	public BrandResponse createBrand(@Valid @RequestBody BrandRequest request) {
		return service.createBrand(request);
	}
    
	@Operation(summary = "GET Mapping",description = "Fetch All Brands")
    @GetMapping
    public List<BrandResponse> getAllBrands(){
    	return service.getAllBrand();
    }
    
	@Operation(summary = "GET Mapping",description = "Fetch Brand By Id")
    @GetMapping("/{id}")
    public BrandResponse getBrandById(@PathVariable int id) {
    	return service.getBrandById(id);
    }
    
	@Operation(summary = "Delete Mapping",description = "Delete Brand By Id")
    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable int id) {
    	service.deleteById(id);
    	return "Brand Delete Successfully";
    }
    
}

















