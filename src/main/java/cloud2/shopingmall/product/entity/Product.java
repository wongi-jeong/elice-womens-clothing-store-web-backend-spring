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
<<<<<<< HEAD
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
    private ProductDisplay productDisplay;

    public enum ProductStatus {
        ONE, TWO, THREE,
    }
=======
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

    public Product(String name, Size size, Color color, Integer quantity, ProductStatus status){
        this.name = name;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.status = status;
    }

    public enum ProductStatus {
        ONE, TWO, THREE;
    }

    public enum Size {
        ONE, TWO, THREE;
    }

    public enum Color {
        ONE, TWO, THREE;
    }



>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
}
