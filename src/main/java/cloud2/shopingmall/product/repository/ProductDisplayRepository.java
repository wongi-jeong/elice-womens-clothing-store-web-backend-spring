package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductDisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDisplayRepository extends JpaRepository<ProductDisplay,Long> {

    @Query("SELECT distinct pd FROM ProductDisplay pd join fetch pd.products where pd.id = :id")
    ProductDisplay findProductDisplayWithOptions(@Param("id") Long id);

    @Query("SELECT distinct pd FROM ProductDisplay pd join fetch pd.productDisplayImages where pd.id = :id")
    ProductDisplay findProductDisplayWithImages(@Param("id") Long id);

    ProductDisplay findByName(String name);
}
