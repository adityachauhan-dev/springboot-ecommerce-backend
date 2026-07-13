package in.main.service;

import java.util.List;

import in.main.dto.request.CategoryRequest;
import in.main.dto.response.CategoryResponse;

public interface ICategoryService {
	
	CategoryResponse createCategory(CategoryRequest request);
	
	List<CategoryResponse> getAllCategories();
	
	CategoryResponse getCategoryById(int id);
	
	void deleteCategory(int id);
	
}