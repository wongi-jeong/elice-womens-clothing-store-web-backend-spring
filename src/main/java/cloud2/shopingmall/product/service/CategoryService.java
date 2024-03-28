package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private Category foundCategory;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        foundCategory = categoryRepository.findById(category.getId())
                .orElseThrow(NoSuchElementException::new);

        Optional.ofNullable(category.getCategoryName())
                .ifPresent(categoryName -> foundCategory = foundCategory.toBuilder()
                        .categoryName(categoryName)
                        .build());

        foundCategory = foundCategory.toBuilder()
                .categoryRank(category.getCategoryRank())
                .build();

        return categoryRepository.save(foundCategory);
    }

    public void deleteCategory(Long id) {
        foundCategory = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        categoryRepository.delete(foundCategory);
    }
}
