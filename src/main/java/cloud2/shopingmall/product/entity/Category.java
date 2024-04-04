package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer level;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<CategoryProduct> categoryProducts = new ArrayList<>();

    public Category(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

}
