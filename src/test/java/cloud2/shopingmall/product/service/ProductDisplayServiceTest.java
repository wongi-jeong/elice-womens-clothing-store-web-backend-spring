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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
@Import(TestConfig.class)
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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void findProductDisplayWithOptionsAndImagesTest(){

        ProductDisplay pd3 = productDisplayService.getProductDisplayWithOptionAndImages(1L);
        assertEquals(pd3.getProducts().get(0).getSize(),Product.Size.ONE);
        assertEquals(pd3.getProducts().get(1).getSize(),Product.Size.TWO);
        assertEquals(pd3.getProductDisplayImages().get(0).getImageUrl(),"eliceImage");
        assertEquals(pd3.getProductDisplayImages().get(1).getImageUrl(),"eliceImage2");
    }


    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void deleteProductDisplayTest(){
        assertThrows(RuntimeException.class, ()->{productDisplayService.deleteProductDisplay(1L);});
        productDisplayService.deleteProductDisplay(2L);
        assertEquals(productDisplayService.getProductDisplay(2L), null);
    }




}
