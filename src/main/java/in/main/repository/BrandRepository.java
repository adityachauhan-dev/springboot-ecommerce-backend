package in.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.main.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

	boolean existsByName(String name);
}
