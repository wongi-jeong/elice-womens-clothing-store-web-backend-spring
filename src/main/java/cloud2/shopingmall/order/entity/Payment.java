package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
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
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer payTotalPrice;

    @Column
    private PayStatus payStatus;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders order;

    public enum PayStatus {
        ONE, TWO, THREE,
    }
}
