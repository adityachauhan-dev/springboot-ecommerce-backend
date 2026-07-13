package in.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.main.entity.Order;
import in.main.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser(User user);
	
}