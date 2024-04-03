package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.categoryProducts WHERE c.id = : id")
    Category findCategoryWithCategoryProducts(@Param("id") Long id);
}
