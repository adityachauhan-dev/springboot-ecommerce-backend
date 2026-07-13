package in.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import in.main.dto.request.ProductRequest;
import in.main.dto.response.ProductResponse;
import in.main.entity.Brand;
import in.main.entity.Category;
import in.main.entity.Product;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.BrandRepository;
import in.main.repository.CategoryRepository;
import in.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	private final ProductRepository repository;
	private final CategoryRepository categoryRepository;
	private final BrandRepository brandRepository;
	private final FileStorageService fileStorageService;

	@Override
	public ProductResponse createProduct(ProductRequest request) {

		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
		Brand brand = brandRepository.findById(request.getBrandId())
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

		String imageName = null;
		if (request.getImage() != null && !request.getImage().isEmpty()) {
			imageName = fileStorageService.uploadFile(request.getImage());
		}

		Product product = Product.builder().name(request.getName()).imageUrl(imageName)
				.description(request.getDescription()).price(request.getPrice()).stock(request.getStock())
				.category(category).brand(brand).build();
		product = repository.save(product);

		return ProductResponse.builder().id(product.getId()).name(product.getName()).imageUrl(product.getImageUrl())
				.description(product.getDescription()).price(product.getPrice()).stock(product.getStock())
				.categoryName(category.getName()).BrandName(brand.getName()).build();
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = repository.findAll();
		List<ProductResponse> responses = new ArrayList<ProductResponse>();
		for (Product product : products) {
			ProductResponse response = ProductResponse.builder().id(product.getId()).name(product.getName())
					.imageUrl(product.getImageUrl()).description(product.getDescription()).price(product.getPrice())
					.stock(product.getStock()).categoryName(product.getCategory().getName())
					.BrandName(product.getBrand().getName()).build();
			responses.add(response);
		}
		return responses;
	}

	@Override
	public ProductResponse getProductById(int id) {

		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found"));

		return ProductResponse.builder().id(product.getId()).name(product.getName()).imageUrl(product.getImageUrl())
				.description(product.getDescription()).price(product.getPrice()).stock(product.getStock())
				.categoryName(product.getCategory().getName()).BrandName(product.getBrand().getName()).build();

	}

	@Override
	public void deleteProduct(int id) throws IOException {
		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found"));

		if (product.getImageUrl() != null) {
			Files.deleteIfExists(Paths.get("uploads", product.getImageUrl()));
		}

		repository.delete(product);
	}

	@Override
	public Page<ProductResponse> getAllProductsByPage(int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<Product> products = repository.findAll(pageable);

		return products.map(product -> ProductResponse.builder().id(product.getId()).name(product.getName())
				.imageUrl(product.getImageUrl()).description(product.getDescription()).price(product.getPrice())
				.stock(product.getStock()).BrandName(product.getBrand().getName())
				.categoryName(product.getCategory().getName()).build());
	}

	@Override
	public List<ProductResponse> searchProducts(String name) {
		List<Product> products = repository.findByNameContainingIgnoreCase(name);
		List<ProductResponse> responses = new ArrayList<ProductResponse>();
		for (Product product : products) {
			responses.add(ProductResponse.builder().id(product.getId()).name(product.getName())
					.imageUrl(product.getImageUrl()).description(product.getDescription()).price(product.getPrice())
					.stock(product.getStock()).BrandName(product.getBrand().getName())
					.categoryName(product.getCategory().getName()).build());
		}
		return responses;
	}

}