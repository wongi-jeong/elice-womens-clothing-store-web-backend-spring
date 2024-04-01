package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.dto.CategoryDTO;
import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.mapper.ProductMainMapper;
import cloud2.shopingmall.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductMainMapper.CategoryMapper categoryMapper;
    private Category foundCategory;

    public CategoryService(CategoryRepository categoryRepository, ProductMainMapper.CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Category saveCategory(CategoryDTO categoryDTO) {
        return categoryRepository.save(categoryMapper.toEntity(categoryDTO));
    }

    public Category updateCategory(CategoryDTO categoryDTO) {
        foundCategory = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(NoSuchElementException::new);

        foundCategory.toBuilder()
                .categoryName(categoryDTO.getCategoryName())
                .categoryRank(categoryDTO.getCategoryRank())
                .build();

        return categoryRepository.save(foundCategory);
    }

    public void deleteCategory(Long id) {
        foundCategory = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        categoryRepository.delete(foundCategory);
    }
}
