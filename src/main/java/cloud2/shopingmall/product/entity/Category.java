package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private Integer categoryRank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<CategoryProductDisplay> categoryProductDisplays = new ArrayList<>();

    public Category(String categoryName, Integer categoryRank) {
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
    }

}
