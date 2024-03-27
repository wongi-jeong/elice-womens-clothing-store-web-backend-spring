package cloud2.shopingmall.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String senderName;

    @Column
    private String senderPhoneNumber;

    @Column
    private String senderAddress;

    @Enumerated(EnumType.STRING)
    private SenderStatus senderStatus;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveredAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;


    public enum SenderStatus {
        PREPARING_FOR_DELIVERY("배송준비"),
        IN_TRANSIT("배송중"),
        DELIVERED("배송완료"),
        ;

        private String key;

        SenderStatus(String key) {
            this.key = key;
        }
    }


}
