package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.ProductDisplayImage;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

=======
>>>>>>> 77743b20e2bb91b9f8ff79c810bddb370c26d50f
import java.util.List;

public interface ProductDisplayImageRepository extends JpaRepository<ProductDisplayImage,Long> {
    public List<ProductDisplayImage> findByProductDisplay_Id(Long id);
    public void deleteByProductDisplay_Id(Long id);
}
