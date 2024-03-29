package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.product.entity.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {
    private Long orderId;
    private Integer orderNumber;
    private LocalDateTime orderCreatedAt;
    private LocalDateTime orderModifiedAt;
    private Map<ProductDetails,Integer> products;
    private Orders.OrderStatus orderStatus;

}
