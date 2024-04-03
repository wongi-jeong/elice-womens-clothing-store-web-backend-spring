package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.entity.CategoryProduct;
import cloud2.shopingmall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    List<CategoryProduct> findByCategory(Category category);

    List<CategoryProduct> findByProduct(Product product);
}
