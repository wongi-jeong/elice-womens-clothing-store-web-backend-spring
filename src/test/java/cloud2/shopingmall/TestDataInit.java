package cloud2.shopingmall;

import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.entity.ProductDisplayImage;
import cloud2.shopingmall.product.repository.ProductDisplayImageRepository;
import cloud2.shopingmall.product.repository.ProductDisplayRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class TestDataInit implements ApplicationRunner {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDisplayRepository productDisplayRepository;
    @Autowired
    ProductDisplayImageRepository productDisplayImageRepository;

    @Override
    public void run(ApplicationArguments args) {
        initData();
    }
    public void initData(){
        ProductDisplay pd = new ProductDisplay("elice", "description", "url");
        Product product = new Product("elice", Product.Size.ONE, Product.Color.ONE, 100, Product.ProductStatus.ONE);
        Product product2 = new Product("elice2", Product.Size.TWO, Product.Color.TWO, 200, Product.ProductStatus.TWO);

        ProductDisplayImage productDisplayImage = new ProductDisplayImage("eliceImage", "description");
        ProductDisplayImage productDisplayImage2 = new ProductDisplayImage("eliceImage2", "description");


        pd.getProducts().add(product);
        pd.getProducts().add(product2);
        pd.getProductDisplayImages().add(productDisplayImage);
        pd.getProductDisplayImages().add(productDisplayImage2);
        product.setProductDisplay(pd);
        product2.setProductDisplay(pd);
        productDisplayImage.setProductDisplay(pd);
        productDisplayImage2.setProductDisplay(pd);

        productDisplayRepository.save(pd);
        productRepository.save(product);
        productRepository.save(product2);
        productDisplayImageRepository.save(productDisplayImage);
        productDisplayImageRepository.save(productDisplayImage2);


        ProductDisplay pd2 = new ProductDisplay("elice", "description", "url");
        ProductDisplayImage productDisplayImage3 = new ProductDisplayImage("eliceImage", "description");
        ProductDisplayImage productDisplayImage4 = new ProductDisplayImage("eliceImage2", "description");

        pd2.getProductDisplayImages().add(productDisplayImage3);
        pd2.getProductDisplayImages().add(productDisplayImage4);
        productDisplayImage3.setProductDisplay(pd2);
        productDisplayImage4.setProductDisplay(pd2);

        productDisplayRepository.save(pd2);
        productDisplayImageRepository.save(productDisplayImage3);
        productDisplayImageRepository.save(productDisplayImage4);

    }
}
