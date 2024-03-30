package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Long> {
}
