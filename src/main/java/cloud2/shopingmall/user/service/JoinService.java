package cloud2.shopingmall.user.service;


import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;


    public JoinService(
            UserRepository userRepository,
            UserProfileRepository userProfileRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserMapper userMapper,
            UserProfileMapper userProfileMapper) {

        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
    }

    public void joinProcess(UserDTO userDTO, UserProfileDTO userProfileDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            // 현재 존재하는 경우 바로 리턴
            return;
        }

        // 없을 경우 다음 로직 실행
        // User Entity 부분
        User user = userMapper.toEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호를 암호화하여 저장
        user.setUserRole(User.UserRole.ADMIN); // Role 부여

        // UserProfile Entity 부분
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);


        userRepository.save(user);
    }
}
