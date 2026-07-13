package in.main.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import in.main.dto.request.ReviewRequest;
import in.main.dto.response.ReviewResponse;
import in.main.entity.Product;
import in.main.entity.Review;
import in.main.entity.User;
import in.main.exception.ResourceNotFoundException;
import in.main.repository.ProductRepository;
import in.main.repository.ReviewRepository;
import in.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	@Override
	public ReviewResponse createReview(ReviewRequest request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		Review review = Review.builder().rating(request.getRating()).comment(request.getComment()).user(user)
				.product(product).build();

		review = reviewRepository.save(review);

		return ReviewResponse.builder().reviewId(review.getId()).userName(user.getName()).productName(product.getName())
				.rating(review.getRating()).comment(review.getComment()).build();
	}

	@Override
	public List<ReviewResponse> getReviewsByProduct(int productId) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		List<Review> reviews = reviewRepository.findByProduct(product);

		List<ReviewResponse> responses = new ArrayList<>();

		for (Review review : reviews) {

			responses.add(ReviewResponse.builder().reviewId(review.getId()).userName(review.getUser().getName())
					.productName(product.getName()).rating(review.getRating()).comment(review.getComment()).build());
		}

		return responses;
	}

	@Override
	public List<ReviewResponse> getReviewsByUser(int userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		List<Review> reviews = reviewRepository.findByUser(user);

		List<ReviewResponse> responses = new ArrayList<>();

		for (Review review : reviews) {

			responses.add(ReviewResponse.builder().reviewId(review.getId()).userName(user.getName())
					.productName(review.getProduct().getName()).rating(review.getRating()).comment(review.getComment())
					.build());
		}

		return responses;
	}

	@Override
	public void deleteReview(int reviewId) {

		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("Review not found"));

		reviewRepository.delete(review);
	}

}
