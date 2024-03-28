package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Cart;
import cloud2.shopingmall.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long cartId;
    private Long productId;
    private String productName;
    private int count; //상품 개수
}
