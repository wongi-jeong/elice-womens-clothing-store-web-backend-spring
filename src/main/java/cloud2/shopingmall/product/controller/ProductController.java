package cloud2.shopingmall.product.controller;


import cloud2.shopingmall.product.dto.ProductDTO;
import cloud2.shopingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }


//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> getProducts(){
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(productService.getProductsDTO());
//    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size",  defaultValue = "10") int size){

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductsDTO(page - 1, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO.ProductWithDetailsAndBodiesDTO> getProductWithDetailsAndBodies(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductWithDetailsAndBodiesDTO(id));

    }

    @PostMapping
    public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO productDTO){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProductDTO(productDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@RequestBody ProductDTO productDTO, @PathVariable(name = "id") Long id){
        productDTO.setId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductDTO(productDTO));

    }

    @PutMapping("/{id}/on")
    public ResponseEntity onProduct(@PathVariable(name = "id") Long id){
        productService.onProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PutMapping("/{id}/off")
    public ResponseEntity offProduct(@PathVariable(name = "id") Long id){
        productService.offProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).build();

    }


}
