package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column
    private String categoryName;

    @Column
    private Integer categoryRank;
=======
    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private Integer categoryRank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Category> categories = new ArrayList<>();

    public Category(String categoryName, Integer categoryRank) {
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
    }
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
}
