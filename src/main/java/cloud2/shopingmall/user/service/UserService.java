package cloud2.shopingmall.user.service;


import cloud2.shopingmall.common.exception.PasswordMismatchException;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.repository.UserRepository;
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
    public boolean joinProcess(UserDTO.Join userDTO, UserProfileDTO.Join userProfileDTO) throws PasswordMismatchException {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String secondPassword = userDTO.getSecondPassword();
        String email = userProfileDTO.getEmail();
        String phoneNumber = userProfileDTO.getPhoneNumber();
        
        Boolean condition;
        
        // 비밀번호를 제대로 두 번 입력했는지 확인
        condition = !password.equals(secondPassword);
        
        if(condition){
            throw new PasswordMismatchException("입력한 비밀번호가 일치하지 않습니다.");
        }

        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        condition = userRepository.existsByUsername(username);

        if (condition) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // repository에 유저 이메일이 존재하는지 체크 존재하는 경우 true 없으면 false
        condition = userProfileRepository.existsByEmail(email);
        if (condition) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");

        }

        // repository에 유저 핸드폰 번호가 존재하는지 체크 존재하는 경우 true 없으면 false
        condition = userProfileRepository.existsByPhoneNumber(phoneNumber);

        if (condition) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        // 모두 패스 한 경우 다음 로직 실행
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
        userProfile.setGender(userProfileDTO.getGender().getKey());
        userProfileRepository.save(userProfile);

        return true;
    }

    // 아이디 찾기 기능
    public String findUserId(UserProfileDTO.FindUser findUserDTO) {

        String email = findUserDTO.getEmail();
        String phoneNumber = findUserDTO.getPhoneNumber();
        String source = findUserDTO.getSource().getKey();

        UserProfile userProfile = new UserProfile();

        if (source.equals("phone")) {
            if (phoneNumber == null) {
                throw new IllegalArgumentException("전화번호를 입력해 주세요.");
            }
            if (!userProfileRepository.existsByPhoneNumber(phoneNumber)) {
                throw new IllegalArgumentException("존재하는 전화번호가 없습니다.");
            }
            userProfile = userProfileRepository.findByPhoneNumber(findUserDTO.getPhoneNumber());
        }

        if (source.equals("email")) {
            if (email == null) {
                throw new IllegalArgumentException("이메일을 입력해 주세요.");

            }
            if (!userProfileRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("존재하는 이메일이 없습니다.");
            }
            userProfile = userProfileRepository.findByEmail(findUserDTO.getEmail());
        }

        String findID = userProfile.getUser().getUsername();

        return findID;
    }
}
