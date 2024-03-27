package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer orderCount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
