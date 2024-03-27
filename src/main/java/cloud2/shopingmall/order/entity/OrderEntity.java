package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
import cloud2.shopingmall.order.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "order_user_id")
    private OrderUserEntity orderUserEntity;

    public enum OrderStatus {
        ONE, TWO, THREE,
    }

}
