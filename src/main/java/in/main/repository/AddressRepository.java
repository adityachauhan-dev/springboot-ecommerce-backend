package in.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.main.entity.Address;
import in.main.entity.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	List<Address> findByUser(User user);
	
}