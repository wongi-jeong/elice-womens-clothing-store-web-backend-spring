package cloud2.shopingmall.user.entity;

import cloud2.shopingmall.user.dto.UserProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String userPhoneNumber;

    @Column
    private String userAddress;

    @Column
    private UserProfileDTO.Gender gender;

    @Column
    private String birthDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
