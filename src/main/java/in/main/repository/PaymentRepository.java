package in.main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.main.entity.Order;
import in.main.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Optional<Payment> findByOrder(Order order);
}
