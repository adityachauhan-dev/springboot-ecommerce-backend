package in.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.request.ReviewRequest;
import in.main.dto.response.ReviewResponse;
import in.main.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "Review APIs",description = "Manage Review")
public class ReviewController {

	private final ReviewService reviewService;

	@Operation(summary = "POST Mapping",description = "Create Review")
	@PostMapping
	public ReviewResponse createReview(@Valid @RequestBody ReviewRequest request) {

		return reviewService.createReview(request);
	}

	@Operation(summary = "GET Mapping",description = "Fetch Review By Product Id")
	@GetMapping("/{productId}")
	public List<ReviewResponse> getReviewsByProduct(@PathVariable int productId) {

		return reviewService.getReviewsByProduct(productId);
	}

	@Operation(summary = "Get Mapping",description = "Fetch Review By User Id")
	@GetMapping("/{userId}")
	public List<ReviewResponse> getReviewsByUser(@PathVariable int userId) {

		return reviewService.getReviewsByUser(userId);
	}

	@Operation(summary = "Delete Mapping",description = "Delete Review By ReviewId")
	@DeleteMapping("/{reviewId}")
	public String deleteReview(@PathVariable int reviewId) {

		reviewService.deleteReview(reviewId);

		return "Review deleted successfully";
	}

}
