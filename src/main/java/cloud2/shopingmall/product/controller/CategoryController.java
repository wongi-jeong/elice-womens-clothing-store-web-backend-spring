package cloud2.shopingmall.product.controller;

import cloud2.shopingmall.product.dto.CategoryDTO;
import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
