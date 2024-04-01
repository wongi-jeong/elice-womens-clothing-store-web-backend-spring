package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductBody;
import cloud2.shopingmall.product.entity.ProductDetails;
import cloud2.shopingmall.product.repository.ProductBodyRepository;
import cloud2.shopingmall.product.repository.ProductDetailsRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductBodyService {

    private final ProductBodyRepository productBodyRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductBodyService(ProductBodyRepository productBodyRepository, ProductRepository productRepository) {
        this.productBodyRepository = productBodyRepository;
        this.productRepository = productRepository;
    }

    public ProductBody getProductBody(Long id) {
        return productBodyRepository.findById(id).orElse(null);
    }

    public List<ProductBody> getProductBodiesByProductId(Long productId) {
        return productBodyRepository.findByProduct_Id(productId);
    }

-
    public List<ProductBody> saveProductBodies(List<ProductBody> productBodies, Long productId) {
        Product product = productRepository.getReferenceById(productId);
        for (ProductBody productBody : productBodies) {
            productBody.setProduct(product);

        }
        product.setProductBodies(productBodies);

        return productBodyRepository.saveAll(productBodies);
    }

    public List<ProductBody> updateProductBodies(List<ProductBody> productBodies) {
        if (productBodies.get(0).getId() == null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productBodyRepository.saveAll(productBodies);
    }


    public void deleteProductBodies(Long productId) {
        List<ProductBody> productBodies = productBodyRepository.findByProduct_Id(productId);
        Product product = productRepository.getReferenceById(productId);
        product.setProductBodies(null);
        for (ProductBody productBody : productBodies) {
            productBody.setProduct(null);
        }
        productBodyRepository.deleteAll(productBodies);
    }

}
