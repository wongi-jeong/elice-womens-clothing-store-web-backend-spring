package cloud2.shopingmall.product.service;


import cloud2.shopingmall.TestConfig;
import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.repository.ProductDisplayRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
@Import(TestConfig.class)
public class ProductServiceTest {

    private final ProductDisplayRepository productDisplayRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    @Autowired
    public ProductServiceTest(ProductDisplayRepository productDisplayRepository, ProductRepository productRepository, ProductService productService) {

        this.productDisplayRepository = productDisplayRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }



    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void deleteProductTest(){
        ProductDisplay productDisplay = productDisplayRepository.findProductDisplayWithOptions(1L);
        assertEquals(productDisplay.getProducts().size(),2);

        productService.deleteProduct(2L);
        productDisplay = productDisplayRepository.findProductDisplayWithOptions(1L);

        assertEquals(productService.getProduct(2L), null);
        assertEquals(productDisplay.getProducts().size(),1);

    }




}
