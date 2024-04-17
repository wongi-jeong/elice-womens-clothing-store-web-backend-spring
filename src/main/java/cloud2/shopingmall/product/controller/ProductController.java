package cloud2.shopingmall.product.controller;


import cloud2.shopingmall.product.dto.ProductBodyDTO;
import cloud2.shopingmall.product.dto.ProductDTO;
import cloud2.shopingmall.product.dto.ProductImageDTO;
import cloud2.shopingmall.product.entity.ProductBody;
import cloud2.shopingmall.product.entity.ProductImage;
import cloud2.shopingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
    public ResponseEntity<ProductDTO.ProductWithDetailsAndImagesDTO> getProductWithDetailsAndBodies(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductWithAllDTO(id));

    }

//    @PostMapping
//    public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO productDTO){
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(productService.saveProductDTO(productDTO));
//
//    }

    @PostMapping
    public ResponseEntity<ProductDTO.ProductWithDetailsAndImagesDTO> postProductWithImages(@RequestParam("title") String title,
                                                        @RequestParam("price") Integer price,
                                              @RequestPart("productImages") List<MultipartFile> productImagesFile,
                                              @RequestPart("productBodies") List<MultipartFile> productBodiesFile) {


        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
        String baseUrl = "http://localhost:8080/files/";

        // 제목 디렉토리 생성
        File titleDir = new File(uploadDir);
        if (!titleDir.exists()) {
            titleDir.mkdirs();
        }

        List<ProductImageDTO> productImageDTOList = new ArrayList<>();
        List<ProductBodyDTO> productBodyDTOList = new ArrayList<>();

        // 이미지 파일 저장
        for (MultipartFile file : productImagesFile) {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            String imageUrl = "/images/" + fileName;

            try {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int index = fileName.lastIndexOf(".");
            String ext = fileName.substring(index + 1).toLowerCase();
            Integer sizeKB = (int) file.getSize()/1000;

            ProductImageDTO productImageDTO = ProductImageDTO.builder()
                    .url(imageUrl)
                    .sizeKB(sizeKB)
                    .sequence(productImageDTOList.size()+1)
                    .imageFormat(ProductImage.ImageFormat.fromFormat(ext))
                    .build();
            productImageDTOList.add(productImageDTO);
        }

        // 본문 파일 저장
        for (MultipartFile file : productBodiesFile) {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            String imageUrl = "/images/" + fileName;

            try {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int index = fileName.lastIndexOf(".");
            String ext = fileName.substring(index + 1).toLowerCase();
            Integer sizeKB = (int) file.getSize()/1000;

            ProductBodyDTO productBodyDTO = ProductBodyDTO.builder()
                    .url(imageUrl)
                    .sizeKB(sizeKB)
                    .sequence(productBodyDTOList.size()+1)
                    .imageFormat(ProductBody.ImageFormat.fromFormat(ext))
                    .build();
            productBodyDTOList.add(productBodyDTO);
        }

        ProductDTO.ProductWithDetailsAndImagesDTO productDTO = ProductDTO.ProductWithDetailsAndImagesDTO.builder()
                .productBodyDTOList(productBodyDTOList)
                .productImageDTOList(productImageDTOList)
                .name(title)
                .price(price)
                .imageUrl(productImageDTOList.get(0).getUrl())
                .build();
        System.out.println(productDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.saveProductWithImagesDTO(productDTO));
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
