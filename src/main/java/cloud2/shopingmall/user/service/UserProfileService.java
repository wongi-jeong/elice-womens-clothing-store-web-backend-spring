package cloud2.shopingmall.user.service;


import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileService(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    public UserProfile create(UserProfile userProfile, User user) {
        userProfile.setUser(user);

        return userProfileRepository.save(userProfile);
    }
}
