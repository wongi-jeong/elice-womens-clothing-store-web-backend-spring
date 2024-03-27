package cloud2.shopingmall.product.repository;


import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ProductDisplayRepositoryTest {

    private final ProductDisplayRepository productDisplayRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductDisplayRepositoryTest(ProductDisplayRepository productDisplayRepository, ProductRepository productRepository) {

        this.productDisplayRepository = productDisplayRepository;
        this.productRepository = productRepository;
    }



    @Test
    public void findProductDisplayWithOptionsTest(){
        ProductDisplay pd = new ProductDisplay("elice", "description", "url");
        Product product = new Product("elice", Product.Size.ONE, Product.Color.ONE, 100, Product.ProductStatus.ONE);
        Product product2 = new Product("elice2", Product.Size.TWO, Product.Color.TWO, 200, Product.ProductStatus.TWO);
        pd.getProducts().add(product);
        pd.getProducts().add(product2);
        product.setProductDisplay(pd);
        product2.setProductDisplay(pd);

        ProductDisplay pd2 = productDisplayRepository.save(pd);
        productRepository.save(product);
        productRepository.save(product2);

        ProductDisplay pd3 = productDisplayRepository.findProductDisplayWithOptions(pd2.getId());

        assertEquals(pd3.getProducts().get(0).getColor(), Product.Color.ONE);
        assertEquals(pd3.getProducts().get(1).getColor(), Product.Color.TWO);





    }



}
