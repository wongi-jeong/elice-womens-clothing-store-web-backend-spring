package cloud2.shopingmall.user.service;


import cloud2.shopingmall.user.dto.UserDTO;
<<<<<<< HEAD
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
>>>>>>> dev
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private UserService userService;
    private UserProfileService userProfileService;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    public JoinService(UserService userService, UserProfileService userProfileService, UserMapper userMapper, UserProfileMapper userProfileMapper) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
    }


    public void joinProcess(UserDTO userDTO, UserProfileDTO userProfileDTO) {

        String username = userDTO.getUsername();

        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        Boolean isExist = userService.UserIsExist(username);

        if (isExist) {
            // 현재 존재하는 경우 바로 리턴
            return;
        }

<<<<<<< HEAD
        User data = new User();
=======
        // 없을 경우 다음 로직 실행
        // DTO -> Entity 변환
        User user = userMapper.toEntity(userDTO);
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);
>>>>>>> dev

        // User DB에 생성
        User savedUser = userService.create(user);

        // UserProfile DB에 생성
        UserProfile savedProfileUser = userProfileService.create(userProfile,savedUser);

    }
}
