package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.entity.ProductDisplayImage;
import cloud2.shopingmall.product.repository.ProductDisplayImageRepository;
import cloud2.shopingmall.product.repository.ProductDisplayRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDisplayRepository productDisplayRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDisplayRepository productDisplayRepository){
        this.productRepository = productRepository;
        this.productDisplayRepository = productDisplayRepository;
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByProductDisplayId(Long productDisplayId){
        return productRepository.findByProductDisplay_Id(productDisplayId);
    }


    public Product saveProduct(Product product, Long productDisplayId){
        ProductDisplay productDisplay = productDisplayRepository.getReferenceById(productDisplayId);
        product.setProductDisplay(productDisplay);

        return productRepository.save(product);
    }

    public Product updateProductDisplay(Product product){
        if (product.getId() == null){
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productRepository.save(product);
    }


    public void offProduct(Long id){
        try{
            Product product = productRepository.getReferenceById(id);
            product.setStatus(Product.ProductStatus.OFF);

        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public void onProduct(Long id){
        try{
            Product product = productRepository.getReferenceById(id);
            product.setStatus(Product.ProductStatus.ON);

        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }


    public void deleteProduct(Long id){
        Product product = productRepository.getReferenceById(id);
        ProductDisplay productDisplay = product.getProductDisplay();
        productDisplay.getProducts().remove(product);
        productRepository.delete(product);


    }

}
