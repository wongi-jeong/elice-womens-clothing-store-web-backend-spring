package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductBody;
import cloud2.shopingmall.product.repository.ProductBodyRepository;
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
    private final ProductBodyRepository productBodyRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductBodyRepository productBodyRepository) {
        this.productRepository = productRepository;
        this.productBodyRepository = productBodyRepository;
    }


    public List<Product> getProductDisplays() {
        return productRepository.findAll();
    }

    public Page<Product> getProductDisplays(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by("product_display_id").descending()));
    }

    public Product getProductDisplay(Long id) {
        return productRepository.findById(id).orElse(null);


    }

    public Product getProductDisplayWithOptionAndImages(Long id) {
        Product product = productRepository.findProductDisplayWithOptions(id);
        product = productRepository.findProductDisplayWithImages(id);
        return product;


    }

    public Product saveProductDisplay(Product product) {
        if (productRepository.findByName(product.getName()) != null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productRepository.save(product);
    }

    public Product updateProductDisplay(Product product) {
        if (product.getId() == null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productRepository.save(product);
    }


    public void offProductDisplay(Long id) {
        Product product;
        try {
            product = productRepository.getReferenceById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        product.setStatus(Product.ProductDisplayStatus.OFF);
    }

    public void onProductDisplay(Long id) {
        Product product;
        try {
            product = productRepository.getReferenceById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        product.setStatus(Product.ProductDisplayStatus.ON);
    }

    public void deleteProductDisplay(Long id) {
        Product product = productRepository.getReferenceById(id);
        if (product.getProductDetails().size() != 0) {
            throw new RuntimeException();
        }
        List<ProductBody> productBodies = productBodyRepository.findByProduct_Id(id);
        for (ProductBody productBody : productBodies) {
            productBody.setProduct(null);

        }
        product.setProductBodies(new ArrayList<>());
        productBodyRepository.deleteAll(productBodies);
        productRepository.deleteById(id);


    }


}
