package cloud2.shopingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private String senderName;
    private String senderPhoneNumber;
    private String senderAddress;
    private SenderStatus senderStatus;
    private LocalDateTime deliveredAt;

    public enum SenderStatus {
        ONE,TWO,THREE
    }

}
