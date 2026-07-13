package in.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.request.ProductRequest;
import in.main.dto.response.ProductResponse;
import in.main.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product APIs", description = "Manage Product")
public class ProductController {

	
	private final ProductService service;

	@Operation(summary = "POST Mapping", description = "Create Product")
	@PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProductResponse createProduct(@Valid @ModelAttribute ProductRequest request) {
		return service.createProduct(request);
	}

	@Operation(summary = "GET Mapping", description = "Fetch All Product")
	@GetMapping
	public List<ProductResponse> getAllProducts() {
		return service.getAllProducts();
	}

	@Operation(summary = "GET Mapping", description = "Fetch Product By Id")
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}

	@Operation(summary = "DELETE Mapping", description = "Delete Product By Id")
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable int id) throws IOException {
		service.deleteProduct(id);
		return "Product Delete Successfully";
	}

	@Operation(summary = "GET Mapping",description = "Search Product")
	@GetMapping("/search")
	public List<ProductResponse> searchProducts(@RequestParam String name) {
		return service.searchProducts(name);
	}

	@Operation(summary = "GET Mapping", description = "Featch All Product By Page")
	@GetMapping("/page")
	public Page<ProductResponse> getAllProductsByPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return service.getAllProductsByPage(page, size, sortBy);
	}

}