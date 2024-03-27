package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.order.dto.DeliveryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String senderName;

    @Column
    private String senderPhoneNumber;

    @Column
    private String senderAddress;

    @Column
    private SenderStatus senderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deliveredAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;


    public enum SenderStatus {
        ONE("ONE"),
        TWO("TWO"),
        THREE("THREE"),
        ;

        private String key;

        SenderStatus(String key) {
            this.key = key;
        }
    }


}
