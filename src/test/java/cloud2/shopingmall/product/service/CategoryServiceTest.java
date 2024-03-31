package cloud2.shopingmall.product.service;

import cloud2.shopingmall.product.dto.CategoryDTO;
import cloud2.shopingmall.product.entity.Category;
import cloud2.shopingmall.product.mapper.ProductMainMapper;
import cloud2.shopingmall.product.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryServiceTest {

    private final CategoryRepository categoryRepository;
    private final ProductMainMapper.CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceTest(CategoryRepository categoryRepository, ProductMainMapper.CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @DisplayName("GET API - JPA 데이터 조회")
    @Test
    void findCategories() {

    }

    @Test
    void findCategory() {
    }

    @Test
    void saveCategory() {
    }

    @Test
    void updateCategory() {
        // Given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setCategoryName("상의");
        categoryDTO.setCategoryRank(1);

        Category result = categoryRepository.save(categoryMapper.toEntity(categoryDTO));

        // TODO: name이 동일한가?
        assertThat(result.getCategoryName()).isEqualTo("상의");

        // TODO: rank가 동일한가?
        assertThat(result.getCategoryRank()).isEqualTo(1);
    }

    @Test
    void deleteCategory() {
    }
}