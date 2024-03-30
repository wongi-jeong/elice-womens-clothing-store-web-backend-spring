package cloud2.shopingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {

    private String name;
    private Integer price;
    private Integer quantity;
    private ProductStatus productStatus;

    public enum ProductStatus {
        ONE, TWO, THREE
    }
}
