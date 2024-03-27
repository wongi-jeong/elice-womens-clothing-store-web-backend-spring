package cloud2.shopingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String productName;
    private Integer productPrice;
    private Integer productQuantity;
    private ProductStatus productStatus;

    public enum ProductStatus {
        ONE,TWO, THREE,
    }
}
