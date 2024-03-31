package cloud2.shopingmall.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer payTotalPrice;

    @Column
    private PayStatus payStatus;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Orders order;

    public enum PayStatus {
        PENDING_PAYMENT("결제대기"),
        PAYMENT_COMPLETE("결제완료"),
        PAYMENT_CANCELED("결제취소");

        private final String description;

        PayStatus(String description) {
            this.description = description;
        }
    }
}
