package cloud2.shopingmall.product.dto;

import cloud2.shopingmall.product.entity.ProductBody;
import cloud2.shopingmall.product.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {

    private Long id;
    private Integer sequence;
    private String url;
    private String description;
    private Integer sizeKB;
    private ProductImage.ImageFormat imageFormat;

}
