package cloud2.shopingmall.product.repository;

import cloud2.shopingmall.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT distinct p FROM Product p left join fetch p.productDetails where p.id = :id")
    Product findProductWithDetails(@Param("id") Long id);

    @Query("SELECT distinct p FROM Product p left join fetch p.productBodies where p.id = :id")
    Product findProductWithBodies(@Param("id") Long id);

    @Query("SELECT distinct p FROM Product p left join fetch p.productImages where p.id = :id")
    Product findProductWithImages(@Param("id") Long id);

    @Query("""
            SELECT DISTINCT p \s
            FROM Product p
            LEFT JOIN FETCH p.productBodies pb
            LEFT JOIN FETCH p.productDetails pd
            WHERE p.id = :id""")
    Product findProductWithDetailsAndBodies(@Param("id") Long id);

    Product findByName(String name);

    @Query("SELECT distinct p FROM Product p inner join CategoryProduct cp ON cp.product = p inner join cp.category c WHERE c.id = :id")
    List<Product> findProductsWithCategoryId(@Param("id") Long id);
}
