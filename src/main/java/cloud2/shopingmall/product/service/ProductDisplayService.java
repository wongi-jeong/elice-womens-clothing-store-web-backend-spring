package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.entity.ProductDisplay;
import cloud2.shopingmall.product.entity.ProductDisplayImage;
import cloud2.shopingmall.product.repository.ProductDisplayImageRepository;
import cloud2.shopingmall.product.repository.ProductDisplayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDisplayService {

    private final ProductDisplayRepository productDisplayRepository;
    private final ProductDisplayImageRepository productDisplayImageRepository;

    @Autowired
    public ProductDisplayService(ProductDisplayRepository productDisplayRepository, ProductDisplayImageRepository productDisplayImageRepository){
        this.productDisplayRepository = productDisplayRepository;
        this.productDisplayImageRepository = productDisplayImageRepository;
    }


    public List<ProductDisplay> getProductDisplays(){
        return productDisplayRepository.findAll();
    }

    public Page<ProductDisplay> getProductDisplays(int page, int size){
        return productDisplayRepository.findAll( PageRequest.of(page, size, Sort.by("product_display_id").descending()));
    }


    public ProductDisplay getProductDisplayWithOptionAndImages(Long id){
        ProductDisplay productDisplay = productDisplayRepository.findProductDisplayWithOptions(id);
        List<ProductDisplayImage> productDisplayImages = productDisplayImageRepository.findByProductDisplay_Id(id);
        productDisplay.setProductDisplayImages(productDisplayImages);
        return productDisplay;
    }


}
