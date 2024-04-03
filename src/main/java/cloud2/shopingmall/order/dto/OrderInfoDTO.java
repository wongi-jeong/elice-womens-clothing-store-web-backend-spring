package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Orders;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public static class OrderDetailInfo extends OrderInfoDTO {

        private Map<Long, Integer> products;

    }

}
