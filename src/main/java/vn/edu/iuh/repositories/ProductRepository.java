package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByCategory_Id(Long id);
}