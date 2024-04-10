package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
import cloud2.shopingmall.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public enum OrderStatus {
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
        public String getDescription() {
            return description;
        }
        public static Orders.OrderStatus fromDescription(String description) {
            for (Orders.OrderStatus status : Orders.OrderStatus.values()) {
                if (status.getDescription().equals(description)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("No constant with text " + description + " found");
        }
    }
    public Orders(Long id,OrderStatus orderStatus){
        this.id = id;
        this.status =orderStatus;
    }
}
