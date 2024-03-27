package cloud2.shopingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserDTO {

    private String orderUserName;
    private Integer orderNumber;
    private String orderPassword;

}
