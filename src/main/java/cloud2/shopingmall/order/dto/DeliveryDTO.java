package cloud2.shopingmall.order.dto;

import cloud2.shopingmall.order.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.String;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private String senderName;
    private String senderPhoneNumber;
    private String senderAddress;
    private String senderStatus;
    private LocalDateTime deliveredAt;

    public enum SenderStatus {
       PREPARING_FOR_DELIVERY,
        IN_TRANSIT,
        DELIVERED,
    }

    public String getSenderStatus() {
        return senderStatus;
    }

    /*public void setSenderStatus(String senderStatus) {
        if (!isValidSenderStatus(senderStatus)) {
            throw new IllegalArgumentException("유효하지 않은 배송 상태값 입니다.");
        }
        this.senderStatus = senderStatus;
    }*/

    /*private boolean isValidSenderStatus(String senderStatus) {
        for (SenderStatus senderStatus : senderStatus.lines()) {
            if (senderStatus.name().equals(status)) {
                return true;
            }
        } return false;
    }*/

}
