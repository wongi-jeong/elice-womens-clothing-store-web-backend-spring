package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductDisplayImage;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface ProductDisplayImageRepository extends JpaRepository<ProductDisplayImage,Long> {
=======
import java.util.List;

public interface ProductDisplayImageRepository extends JpaRepository<ProductDisplayImage,Long> {

    public List<ProductDisplayImage> findByProductDisplay_Id(Long id);
>>>>>>> dev
}
