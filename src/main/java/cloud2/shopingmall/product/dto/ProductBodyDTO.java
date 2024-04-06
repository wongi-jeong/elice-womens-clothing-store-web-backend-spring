package cloud2.shopingmall.product.dto;

import cloud2.shopingmall.product.entity.ProductBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBodyDTO {

    private Long id;
    private Integer sequence;
    private String url;
    private String description;
    private Integer sizeKB;
    private ProductBody.ImageFormat imageFormat;

}
