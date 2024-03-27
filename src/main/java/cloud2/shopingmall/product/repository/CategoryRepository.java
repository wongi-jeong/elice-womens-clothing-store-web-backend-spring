package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
