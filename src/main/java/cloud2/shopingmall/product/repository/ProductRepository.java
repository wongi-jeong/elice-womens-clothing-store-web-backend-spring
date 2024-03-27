package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
