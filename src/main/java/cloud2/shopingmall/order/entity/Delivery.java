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

    @Column
    private SenderStatus senderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deliveredAt;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders order;


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
