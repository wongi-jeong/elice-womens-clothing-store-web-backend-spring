package cloud2.shopingmall.product.controller;


import cloud2.shopingmall.product.dto.ProductDetailsDTO;
import cloud2.shopingmall.product.service.ProductDetailsService;
import cloud2.shopingmall.product.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductDetailsController {

    private final ProductService productService;
    private final ProductDetailsService productDetailsService;

    @Autowired
    public ProductDetailsController(ProductService productService, ProductDetailsService productDetailsService) {
        this.productService = productService;
        this.productDetailsService = productDetailsService;
    }


    @GetMapping("/{productId}/details")
    public ResponseEntity<List<ProductDetailsDTO>> getProductDetails(@PathVariable(name = "productId") Long productId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productDetailsService.getProductDetailsDTOByProductId(productId));
    }

    @PostMapping("/{productId}/details")
    public ResponseEntity<ProductDetailsDTO> postProductDetails(@RequestBody ProductDetailsDTO productDetailsDTO,
                                                                @PathVariable(name = "productId") Long productId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productDetailsService.saveProductDetailsDTO(productDetailsDTO, productId));

    }

    @PutMapping("/{productId}/details")
    public ResponseEntity<ProductDetailsDTO> patchProductDetails(@RequestBody ProductDetailsDTO productDetailsDTO,
                                                                 @RequestParam(name = "id") Long id) {
        productDetailsDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productDetailsService.updateProductDetailsDTO(productDetailsDTO));

    }

    @DeleteMapping("/{productId}/details")
    public ResponseEntity deleteProductDetails(@RequestParam(name = "id") Long id) {
        productDetailsService.deleteProductDetails(id);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).build();

    }


}
