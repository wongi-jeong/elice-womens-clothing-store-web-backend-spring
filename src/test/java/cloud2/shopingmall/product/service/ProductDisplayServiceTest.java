package cloud2.shopingmall.product.service;


import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.repository.ProductDisplayRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class ProductDisplayServiceTest {

    private final ProductDisplayRepository productDisplayRepository;
    private final ProductRepository productRepository;
    private final ProductDisplayService productDisplayService;
    @Autowired
    public ProductDisplayServiceTest(ProductDisplayRepository productDisplayRepository, ProductRepository productRepository, ProductDisplayService productDisplayService) {

        this.productDisplayRepository = productDisplayRepository;
        this.productRepository = productRepository;
        this.productDisplayService = productDisplayService;
    }
    @Test
    public void findProductDisplayWithOptionsAndImagesTest(){
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

        ProductDisplay pd3 = productDisplayService.getProductDisplayWithOptionAndImages(pd2.getId());






    }


}
