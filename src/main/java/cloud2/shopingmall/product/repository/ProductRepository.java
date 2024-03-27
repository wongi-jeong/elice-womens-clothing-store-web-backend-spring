package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
