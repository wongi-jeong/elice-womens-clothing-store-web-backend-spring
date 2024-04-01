package cloud2.shopingmall.user.entity;

import cloud2.shopingmall.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userRole;

    @Enumerated(EnumType.STRING)
    @Column
    private Status Status;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfile userProfile;


    @Getter
    public enum Status {
        ACTIVE("User_Active"),
        DEACTIVE("User_Deactive"),
        DELETED("User_Deleted");

        private String status;

        Status(String status) {
            this.status = status;
        }


    }



}
