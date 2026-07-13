package in.main.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import in.main.dto.request.ProductRequest;
import in.main.dto.response.ProductResponse;

public interface IProductService {
	
	ProductResponse createProduct(ProductRequest request);
	
	List<ProductResponse> getAllProducts();
	
	ProductResponse getProductById(int id);
	
	void deleteProduct(int id) throws IOException;
	
	Page<ProductResponse> getAllProductsByPage(
			int page,
			int size,
			String sortBy);
	
	List<ProductResponse> searchProducts(String name);
	
}