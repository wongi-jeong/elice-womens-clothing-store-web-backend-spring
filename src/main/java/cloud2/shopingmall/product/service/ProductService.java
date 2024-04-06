package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.dto.ProductAndCategoryResponseDTO;
import cloud2.shopingmall.product.dto.ProductDTO;
import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.entity.CategoryProduct;
import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.product.entity.ProductBody;
import cloud2.shopingmall.product.entity.ProductDetails;
import cloud2.shopingmall.product.mapper.ProductMainMapper;
import cloud2.shopingmall.product.repository.CategoryProductRepository;
import cloud2.shopingmall.product.repository.ProductBodyRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    private final ProductMainMapper.ProductMapper productMapper;
    private final ProductMainMapper.ProductWithDetailsAndBodiesMapper productWithDetailsAndBodiesMapper;
    private final CategoryProductRepository categoryProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductBodyRepository productBodyRepository, ProductMainMapper.ProductMapper productMapper, ProductMainMapper.ProductWithDetailsAndBodiesMapper productWithDetailsAndBodiesMapper, CategoryProductRepository categoryProductRepository) {
        this.productRepository = productRepository;
        this.productBodyRepository = productBodyRepository;
        this.productMapper = productMapper;
        this.productWithDetailsAndBodiesMapper = productWithDetailsAndBodiesMapper;
        this.categoryProductRepository = categoryProductRepository;
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public List<ProductDTO> getProductsDTO() {
        return productMapper.toDto(productRepository.findAll());
    }
    public Page<Product> getProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }
    public Page<ProductDTO> getProductsDTO(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).map(product-> productMapper.toDto(product));
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);


    }

    public ProductDTO.ProductWithDetailsAndBodiesDTO getProductWithAllDTO(Long id) {
//        Product product = productRepository.findProductWithDetailsAndBodies(id);
        Product product = productRepository.findProductWithDetails(id);
        product = productRepository.findProductWithBodies(id);
        product = productRepository.findProductWithImages(id);
        if(product == null){
            //TO DO
            throw new RuntimeException();
        }
        return productWithDetailsAndBodiesMapper.toDto(product);


    }

    public Product getProductWithDetailsAndBodies(Long id) {
//        Product product = productRepository.findProductWithDetailsAndBodies(id);
        Product product = productRepository.findProductWithDetails(id);
        product = productRepository.findProductWithBodies(id);
        return product;


    }

    public Product saveProduct(Product product) {
        if (productRepository.findByName(product.getName()) != null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productRepository.save(product);
    }

    public ProductDTO saveProductDTO(ProductDTO productDTO) {
        if (productRepository.findByName(productDTO.getName()) != null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public Product updateProduct(Product product) {


        return productRepository.save(product);
    }

    public ProductDTO updateProductDTO(ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            //TO DO: need new customException
            throw new RuntimeException();
        }
        Product Product = productRepository.getReferenceById(productDTO.getId());
        productMapper.updateFromDto(productDTO, Product);

        return productMapper.toDto(Product);
    }


    public void offProduct(Long id) {
        Product product;
        try {
            product = productRepository.getReferenceById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        if(product.getStatus() == Product.ProductDisplayStatus.OFF){
            //TO DO
            throw new RuntimeException();
        }
        product.setStatus(Product.ProductDisplayStatus.OFF);
    }

    public void onProduct(Long id) {
        Product product;
        try {
            product = productRepository.getReferenceById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        if(product.getStatus() == Product.ProductDisplayStatus.ON){
            //TO DO
            throw new RuntimeException();
        }
        product.setStatus(Product.ProductDisplayStatus.ON);
    }

    public void deleteProduct(Long id) {
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
