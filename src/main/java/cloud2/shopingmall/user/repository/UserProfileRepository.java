package cloud2.shopingmall.user.repository;

import cloud2.shopingmall.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
<<<<<<< HEAD
=======
    UserProfile findByEmail(String email);

    UserProfile findByPhoneNumber(String phoneNumber);
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312

}
