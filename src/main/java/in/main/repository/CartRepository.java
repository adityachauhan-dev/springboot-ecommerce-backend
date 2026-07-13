package in.main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.main.entity.Cart;
import in.main.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUser(User user);
}