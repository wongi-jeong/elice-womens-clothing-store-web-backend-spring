package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    private Long id;
    private String userName;
    private LocalDateTime CreatedAt;
    private LocalDateTime ModifiedAt;
    private Orders.OrderStatus Status;
    private Integer totalPrice;
    private OrderDetailInfo Detail;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailInfo extends OrderInfoDTO{

        private Map<Long,Integer> products;

    }

}
