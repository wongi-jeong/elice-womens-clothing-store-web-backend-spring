package cloud2.shopingmall.product.entity;

import cloud2.shopingmall.product.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private Integer productPrice;

    @Column
    private Integer productQuantity;

    @Column
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "product_display_id")
    private ProductDisplayEntity productDisplayEntity;

    public enum ProductStatus {
        ONE, TWO, THREE,
    }
}
