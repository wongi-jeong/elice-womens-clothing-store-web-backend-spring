package cloud2.shopingmall.product.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
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
public class ProductDisplay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
