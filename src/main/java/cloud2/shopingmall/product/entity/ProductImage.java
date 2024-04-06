package cloud2.shopingmall.product.entity;

import cloud2.shopingmall.image.entity.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer sequence;

    @Column
    private String url;

    @Column
    private String description;


    @Column
    private Integer sizeKB;

    @Column
    private ProductBody.ImageFormat imageFormat;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public ProductImage(Integer sequence, String url, String description) {
        this.sequence = sequence;
        this.url = url;
        this.description = description;
    }

    public enum ImageFormat {
        JPG, JPEG, PNG, PDF
    }
}