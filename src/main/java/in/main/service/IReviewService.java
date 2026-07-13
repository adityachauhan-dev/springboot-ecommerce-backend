package in.main.service;

import java.util.List;

import in.main.dto.request.ReviewRequest;
import in.main.dto.response.ReviewResponse;

public interface IReviewService {

	ReviewResponse createReview(ReviewRequest request);
	
	void deleteReview(int reviewId);

	List<ReviewResponse> getReviewsByProduct(int productId);

	List<ReviewResponse> getReviewsByUser(int userId);
	
}