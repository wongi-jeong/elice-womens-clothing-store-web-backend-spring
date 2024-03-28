package cloud2.shopingmall.product.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
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
public class ProductDisplay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id;

    @Column
    private String ProductDisplayName;

    @Column
    private String ProductDescription;

=======
    @Column(name="product_display_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private ProductDisplayStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productDisplay")
    private List<ProductDisplayImage> productDisplayImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productDisplay")
    private List<Product> products = new ArrayList<>();

    public enum ProductDisplayStatus {
        ON, OFF;
    }

    public ProductDisplay(String name, String description, String imageUrl){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
}
