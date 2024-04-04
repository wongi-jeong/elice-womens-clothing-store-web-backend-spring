package cloud2.shopingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBodyDTO {

    private Long id;
    private Integer sequence;
    private String imageUrl;
    private String imageDescription;

}
