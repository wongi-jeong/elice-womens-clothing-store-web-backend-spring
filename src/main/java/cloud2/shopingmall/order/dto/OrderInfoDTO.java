package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.product.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    private Long orderNumber;
    private String orderUser;
    private LocalDateTime orderCreatedAt;
    private LocalDateTime orderModifiedAt;
    private Orders.OrderStatus orderStatus;
    private Integer totalPrice;
    private OrderDetailInfo orderDetail;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailInfo {

        private Long orderNumber;
        private String orderUser;
        private LocalDateTime orderCreatedAt;
        private LocalDateTime orderModifiedAt;
        private Orders.OrderStatus orderStatus;
        private Integer totalPrice;
        private Map<Long,Integer> products;
        public OrderDetailInfo(Long orderNumber,String orderUser, LocalDateTime orderCreatedAt, LocalDateTime orderModifiedAt, Orders.OrderStatus orderStatus, Integer totalPrice){
            this.orderNumber = orderNumber;
            this.orderUser = orderUser;
            this.orderCreatedAt = orderCreatedAt;
            this.orderModifiedAt = orderModifiedAt;
            this.orderStatus = orderStatus;
            this.totalPrice = totalPrice;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class productQuantityDTO {

        private Long productId;
        private Integer amount;
    }
}
