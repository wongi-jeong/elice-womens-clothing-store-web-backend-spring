package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDisplayImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_display_image_id")
    private Long id;

    @Column
    private String imageUrl;

    @Column
    private String imageDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_display_id")
    private ProductDisplay productDisplay;


    public ProductDisplayImage(String imageUrl, String imageDescription){
        this.imageUrl = imageUrl;
        this.imageDescription = imageDescription;
    }
}
