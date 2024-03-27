package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String orderUserName;

    @Column
    private Integer orderNumber;

    @Column
    private String orderPassword;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
