package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;
    @Column
    private Size size;
    @Column
    private Color color;
    @Column
    private Integer quantity;

    @Column
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_display_id")
    private ProductDisplay productDisplay;

    public enum ProductStatus {
        ONE, TWO, THREE;
    }

    public enum Size {
        ONE, TWO, THREE;
    }

    public enum Color {
        ONE, TWO, THREE;
    }
}
