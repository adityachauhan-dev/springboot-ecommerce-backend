package in.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.main.dto.request.CategoryRequest;
import in.main.dto.response.CategoryResponse;
import in.main.entity.Category;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

	private final CategoryRepository repository;

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {

		if (repository.existsByName(request.getName())) {
			throw new RuntimeException("Catetory Already Exist");
		}
		Category category = Category.builder().name(request.getName()).description(request.getDescription()).build();
		category = repository.save(category);
		
		return CategoryResponse.builder().id(category.getId()).name(category.getName())
				.description(category.getDescription()).build();
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		
		return repository.findAll().stream().map(category -> 
				CategoryResponse.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.build())
				.toList();
	}

	@Override
	public CategoryResponse getCategoryById(int id) {
		Category category = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not Found"));
		
		return CategoryResponse.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.build();
	}

	@Override
	public void deleteCategory(int id) {
		repository.deleteById(id);
	}


}