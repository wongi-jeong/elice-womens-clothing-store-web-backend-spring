package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductDisplayImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDisplayImageRepository extends JpaRepository<ProductDisplayImage,Long> {

    public List<ProductDisplayImage> findByProductDisplay_Id(Long id);
}
