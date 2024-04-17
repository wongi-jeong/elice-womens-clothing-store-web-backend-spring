package cloud2.shopingmall.product.controller;

import cloud2.shopingmall.product.dto.CategoryDTO;
import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping
//    public ResponseEntity<List<Category>> getAllCategories() {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(categoryService.findCategories());
//    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesDTO() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findCategories());
    }

    @GetMapping("/{superId}")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesBySuperIdDTO(@PathVariable(name = "superId") Long superId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findCategoriesBySuperId(superId));
    }




    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findCategory(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.saveCategory(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(categoryService.updateCategory(categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
