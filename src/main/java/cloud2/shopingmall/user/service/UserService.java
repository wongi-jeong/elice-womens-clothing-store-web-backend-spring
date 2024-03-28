package cloud2.shopingmall.user.service;


import cloud2.shopingmall.user.dto.FindUserDTO;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, UserProfileMapper userProfileMapper, UserRepository userRepository, UserProfileRepository userProfileRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    public void joinProcess(UserDTO userDTO,UserProfileDTO userProfileDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            // 현재 존재하는 경우 바로 리턴
            return;
        }

        // 없을 경우 다음 로직 실행
        // DTO -> Entity 변환
        User user = userMapper.toEntity(userDTO);
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);

        // User DB에 생성
        user.setStatus(User.Status.ACTIVE); // 처음 가입시 계정상태 활성화 상태
        user.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호를 암호화하여 저장
        user.setUserRole("ROLE_ADMIN"); // Role 부여
        User savedUser = userRepository.save(user); // DB에 저장

        // UserProfile DB에 생성
        userProfile.setUser(savedUser);
        userProfile.setGender(userProfileDTO.getGender().getGender());
        userProfileRepository.save(userProfile);
    }

    public String findUserId(FindUserDTO findUserDTO) {
        // 폼 2개 다 null 이면 예외처리
        if (findUserDTO.getEmail() == null && findUserDTO.getPhoneNumber() == null) {
            throw new IllegalArgumentException("이메일 또는 전화번호 중 최소 하나는 제공되어야 합니다.");
        }

        User user = new User();
        UserProfile userProfile = null;

        // email로 아이디 찾기
        if ("email".equals(findUserDTO.getSource().getKey())) {
            userProfile = userProfileRepository.findByEmail(findUserDTO.getEmail());
        }

        // phoneNumber로 아이디 찾기
        if ("phone".equals(findUserDTO.getSource().getKey())) {
            userProfile = userProfileRepository.findByPhoneNumber(findUserDTO.getPhoneNumber());
        }

        // user에 저장된 아이디 찾기
        String findID = null;
        if (userProfile != null && userProfile.getUser() != null) {
            findID = userProfile.getUser().getUsername();
        }

        if (findID == null) {
            throw new IllegalStateException("찾는 ID가 없습니다.");
        }
        return findID;
    }

}
