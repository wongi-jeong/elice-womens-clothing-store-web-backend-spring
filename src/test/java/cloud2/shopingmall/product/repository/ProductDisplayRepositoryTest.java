package cloud2.shopingmall.product.repository;


import cloud2.shopingmall.TestConfig;
import cloud2.shopingmall.TestDataInit;
import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.entity.ProductDisplayImage;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(TestConfig.class)
public class ProductDisplayRepositoryTest {

    private final ProductDisplayRepository productDisplayRepository;
    private final ProductDisplayImageRepository productDisplayImageRepository;
    private final ProductRepository productRepository;


    @Autowired
    public ProductDisplayRepositoryTest(ProductDisplayRepository productDisplayRepository, ProductRepository productRepository, ProductDisplayImageRepository productDisplayImageRepository) {

        this.productDisplayRepository = productDisplayRepository;
        this.productRepository = productRepository;
        this.productDisplayImageRepository = productDisplayImageRepository;

    }

    @BeforeEach
    public void beforeTest(){
    }



    @Test
    public void findProductDisplayWithOptionsTest(){

        ProductDisplay pd3 = productDisplayRepository.findProductDisplayWithOptions(1L);

        assertEquals(Hibernate.isInitialized(pd3.getProducts()), true);
        assertEquals(Hibernate.isInitialized(pd3.getProductDisplayImages()), false);

        assertEquals(pd3.getProductDisplayImages().get(0).getImageUrl(), "eliceImage");
        assertEquals(pd3.getProductDisplayImages().get(1).getImageUrl(), "eliceImage2");
        assertEquals(pd3.getProducts().get(0).getColor(), Product.Color.ONE);
        assertEquals(pd3.getProducts().get(1).getColor(), Product.Color.TWO);


    }



}
