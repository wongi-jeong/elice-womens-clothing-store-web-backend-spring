package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
    List<ProductDetails> findByProduct_Id(Long productId);
}
