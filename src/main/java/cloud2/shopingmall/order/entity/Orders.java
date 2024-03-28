package cloud2.shopingmall.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private OrderStatus orderStatus;


    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCreatedAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderModifiedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_user_id")
    private OrderUser orderUser;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id")
    private List<OrderProduct> orderProducts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public enum OrderStatus {
        PENDING_PAYMENT("결제요청"),
        PAYMENT_COMPLETED("결제완료"),
        PREPARING_FOR_DELIVERY("배송준비"),
        IN_TRANSIT("배송중"),
        DELIVERED("배송완료"),
        ORDER_CANCELLED("주문취소"),
        REFUND_COMPLETED("환불완료");

        private final String description;

        OrderStatus(String description){
            this.description = description;
        }

    }
}
