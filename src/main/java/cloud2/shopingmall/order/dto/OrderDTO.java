package cloud2.shopingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private OrderStatus orderStatus;
    private LocalDateTime orderCreatedAt;
    private LocalDateTime orderModifiedAt;

    public enum OrderStatus {
        PENDING_PAYMENT,
        PAYMENT_COMPLETED,
        PREPARING_FOR_DELIVERY,
        IN_TRANSIT,
        DELIVERED,
        ORDER_CANCELLED,
        REFUND_COMPLETED,
    }
}
