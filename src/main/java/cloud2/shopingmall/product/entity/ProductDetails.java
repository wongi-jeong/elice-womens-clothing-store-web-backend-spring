package cloud2.shopingmall.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_details_id")
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
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Body_id")
    private ProductBody productBody;

    public ProductDetails(String name, Size size, Color color, Integer quantity, ProductStatus status) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.status = status;
    }

    public enum ProductStatus {
        ON, OFF, ONE, TWO
    }

    public enum Size {
        ONE, TWO, THREE
    }

    public enum Color {
        ONE, TWO, THREE
    }


}
