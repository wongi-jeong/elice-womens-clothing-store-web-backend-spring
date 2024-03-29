package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT distinct pd FROM ProductDetails pd join fetch pd.product where pd.id = :id")
    Product findProductDisplayWithOptions(@Param("id") Long id);

    @Query("SELECT distinct pd FROM ProductDetails pd join fetch pd.productBody where pd.id = :id")
    Product findProductDisplayWithImages(@Param("id") Long id);

    Product findByName(String name);
}
