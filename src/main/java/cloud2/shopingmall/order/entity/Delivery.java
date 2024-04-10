package cloud2.shopingmall.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Name;

    @Column
    private String PhoneNumber;

    @Column
    private String PostNumber;
    @Column
    private String Address;
    @Column
    private String addressDetail;

    @Column
    private SenderStatus status;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveredAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;


    public enum SenderStatus {
        PREPARING_FOR_DELIVERY("배송준비"),
        IN_TRANSIT("배송중"),
        DELIVERED("배송완료"),
        ;

        private String description;

        SenderStatus(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
        public static SenderStatus fromDescription(String description) {
            for (SenderStatus status : SenderStatus.values()) {
                if (status.getDescription().equals(description)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("No constant with text " + description + " found");
        }

    }
    public Delivery(Long id, SenderStatus status){
        this.id = id;
        this.status = status;
    }


}
