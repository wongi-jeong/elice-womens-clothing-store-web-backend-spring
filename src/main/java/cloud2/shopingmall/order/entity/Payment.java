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

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime payCreatedAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime payModifiedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    public enum PayStatus {
        PAYMENT_REQUESTED("결제요청"),
        PAYMENT_COMPLETED("결제완료"),
        PAYMENT_FAILED("결제실패"),
        PAYMENT_CANCELLED("결제취소"),
        PAYMENT_REFUNDED("환불"),
        ;

        private final String description;

        PayStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
