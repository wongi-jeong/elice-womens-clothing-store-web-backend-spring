package cloud2.shopingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime orderCreatedAt;
    private LocalDateTime orderModifiedAt;
    private List<OrderProductDTO> products;

    public enum OrderStatus {
        PAYMENT_COMPLETED,
        PREPARING_FOR_DELIVERY,
        IN_TRANSIT,
        DELIVERED,
        ORDER_CANCELLED,
        REFUND_COMPLETED,
    }
}
