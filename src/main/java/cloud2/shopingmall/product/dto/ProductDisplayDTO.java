package cloud2.shopingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDisplayDTO {

    private String ProductDisplayName;
    private String ProductDescription;
    private LocalDateTime ProductDisplayCreatedAt;
    private LocalDateTime ProductDisplayModifiedAt;
}
