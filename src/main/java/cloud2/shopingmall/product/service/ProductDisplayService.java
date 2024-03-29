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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    public ProductDisplay getProductDisplay(Long id){
        return productDisplayRepository.findById(id).orElse(null);


    }

    public ProductDisplay getProductDisplayWithOptionAndImages(Long id){
        ProductDisplay productDisplay = productDisplayRepository.findProductDisplayWithOptions(id);
        productDisplay = productDisplayRepository.findProductDisplayWithImages(id);
        return productDisplay;


    }

    public ProductDisplay saveProductDisplay(ProductDisplay productDisplay){
        if (productDisplayRepository.findByName(productDisplay.getName()) != null){
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productDisplayRepository.save(productDisplay);
    }

    public ProductDisplay updateProductDisplay(ProductDisplay productDisplay){
        if (productDisplay.getId() == null){
            //TO DO: need new customException
            throw new RuntimeException();
        }

        return productDisplayRepository.save(productDisplay);
    }


    public void offProductDisplay(Long id){
        ProductDisplay productDisplay;
        try{
            productDisplay = productDisplayRepository.getReferenceById(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        productDisplay.setStatus(ProductDisplay.ProductDisplayStatus.OFF);
    }

    public void onProductDisplay(Long id){
        ProductDisplay productDisplay;
        try{
            productDisplay = productDisplayRepository.getReferenceById(id);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        productDisplay.setStatus(ProductDisplay.ProductDisplayStatus.ON);
    }

    public void deleteProductDisplay(Long id){
        ProductDisplay productDisplay = productDisplayRepository.getReferenceById(id);
        if(productDisplay.getProducts().size() != 0 ){
            throw new RuntimeException();
        }
        List<ProductDisplayImage> ProductDisplayImages = productDisplayImageRepository.findByProductDisplay_Id(id);
        for (ProductDisplayImage productDisplayImage : ProductDisplayImages) {
            productDisplayImage.setProductDisplay(null);

        }
        productDisplay.setProductDisplayImages(new ArrayList<>());
        productDisplayImageRepository.deleteAll(ProductDisplayImages);
        productDisplayRepository.deleteById(id);


    }




}
