package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}