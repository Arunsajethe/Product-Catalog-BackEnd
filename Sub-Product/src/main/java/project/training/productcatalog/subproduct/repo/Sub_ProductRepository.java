package project.training.productcatalog.subproduct.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.training.productcatalog.subproduct.entity.Sub_Product;

public interface Sub_ProductRepository extends JpaRepository<Sub_Product, Integer> {
	
	List<Sub_Product> findBySname(String sname);

}
