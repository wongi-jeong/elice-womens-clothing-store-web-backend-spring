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

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private UserStatus userStatus;


    @OnDelete(action = OnDeleteAction.CASCADE) // 연결된 모드 데이터 삭제
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile userProfile;

    @Getter
    public enum UserRole {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private String key;

        UserRole(String key) {
            this.key = key;
        }
    }

    public enum UserStatus {
        ONE,TWO, THREE,
    }



}
