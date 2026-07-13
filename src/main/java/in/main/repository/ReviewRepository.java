package in.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.main.entity.Product;
import in.main.entity.Review;
import in.main.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByUser(User user);

	List<Review> findByProduct(Product product);

}