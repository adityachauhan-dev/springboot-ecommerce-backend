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

import in.main.dto.request.CategoryRequest;
import in.main.dto.response.CategoryResponse;
import in.main.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category APIs", description = "Manage Category")
public class CategoryController {

	
	private final CategoryService service;

	@Operation(summary = "POST Mapping",description = "Create Category")
	@PostMapping
	public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
		return service.createCategory(request);
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch All Category")
	@GetMapping
	public List<CategoryResponse> getAllCategory(){
		return service.getAllCategories();
	}
	
	@Operation(summary = "GET Mapping",description = "Fetch Category By Id")
	@GetMapping("/{id}")
	public CategoryResponse getCategoryById(@PathVariable int id) {
		return service.getCategoryById(id);
	}
	
	@Operation(summary = "Delete Mapping",description = "Delete Category By Id")
	@DeleteMapping("/{id}")
	public String deleteCatogryById(@PathVariable int id) {
		service.deleteCategory(id);
		return "Category Delete Successfully";
	}
	
}
